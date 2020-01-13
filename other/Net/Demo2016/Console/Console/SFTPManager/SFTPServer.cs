using log4net;
using System;
using System.Collections.Specialized;
using System.Configuration;
using System.IO;
using System.Net;
using System.Net.Sockets;
using System.Reflection;
using Tamir.SharpSsh.jsch;

namespace Server
{
    public sealed class SFTPServer : IDisposable
    {
        #region Field

        /// <summary>
        /// SFTP通道
        /// </summary>
        private ChannelSftp _channelSftp;

        /// <summary>
        /// 通道
        /// </summary>
        private Channel _channel;

        /// <summary>
        /// 会话
        /// </summary>
        private Session _session;       

        /// <summary>
        /// 日志
        /// </summary>
        private ILog _log;

        /// <summary>
        /// 主机名或IP
        /// </summary>
        private string _hostName;

        /// <summary>
        /// 用户名
        /// </summary>
        private string _userName;

        /// <summary>
        /// 密码
        /// </summary>
        private string _password;

        /// <summary>
        /// 端口号(默认端口为22)
        /// </summary>
        private int _port;

        /// <summary>
        /// 根目录（默认为根目录"/"）
        /// </summary>
        private string _defaultfRemotePath;

        /// <summary>
        /// 私钥文件存放路径
        /// </summary>
        private string _privateKeyFile;

        /// <summary>
        /// 私钥的密码
        /// </summary>
        private string _passphrase;

        /// <summary>
        /// 访问方式
        /// </summary>
        private SFTPConnectTypeEnum _connectWay;

        #endregion

        #region Property

        /// <summary>
        /// SFTP连接状态
        /// </summary>
        public bool IsConnected
        {
            get
            {
                if (_session != null)
                {
                    return _session.isConnected();
                }
                else
                {
                    return false;
                }
            }
        }

        /// <summary>
        /// 服务器文件存放目录
        /// </summary>
        public string DirectoryPath { get; set; }

        #endregion

        /// <summary>
        /// 构造函数
        /// </summary>
        public SFTPServer()
        {
            //初始化日志对象
            _log = LogManager.GetLogger(MethodBase.GetCurrentMethod().DeclaringType);

            //从配置文件中加载凭证信息
            var config = ConfigurationManager.GetSection("SftpServer") as NameValueCollection;
            _hostName = config["HostName"];
            _userName = config["UserName"];
            _password = config["Password"];
            _defaultfRemotePath = config["DefaultfRemotePath"] ?? "/";   //默认值为/
            _port = Convert.ToInt32(config["Port"] ?? "22");             //默认端口为22  
            _privateKeyFile = config["PrivateKeyFile"];
            _passphrase = config["Passphrase"] ?? "";                    //默认为空

            if (!Enum.TryParse(config["ConnectWay"], true, out _connectWay))
            {
                _connectWay = SFTPConnectTypeEnum.FirstPasswordThenPrivateKey;
            }
        }

        #region Public Method

        /// <summary>
        /// 建立SFTP连接
        /// </summary>
        public void ConnectSFTP()
        {
            if (PortIsOpen())
            {
                try
                {
                    switch (_connectWay)
                    {
                        case SFTPConnectTypeEnum.Password:
                            ConnectSFTPByPassword();
                            break;
                        case SFTPConnectTypeEnum.PrivateKey:
                            ConnectSFTPByPrivateKey();
                            break;
                        case SFTPConnectTypeEnum.FirstPasswordThenPrivateKey:
                            ConnectSFTPByPassword();
                            _log.Debug("先尝试密码连接，连接状态：" + IsConnected);
                            if (!IsConnected)
                            {
                                _log.Debug("尝试密钥连接");
                                ConnectSFTPByPrivateKey();
                            }
                            break;
                        case SFTPConnectTypeEnum.FirstPrivateKeyThenPassword:
                            ConnectSFTPByPrivateKey();
                            _log.Debug("先尝试密钥连接，连接状态：" + IsConnected);
                            if (!IsConnected)
                            {
                                _log.Debug("尝试密码连接");
                                ConnectSFTPByPassword();
                            }
                            break;
                        default:
                            break;
                    }
                }
                catch (Exception e)
                {
                    _log.Error("选择连接SFTP方式异常！", e);
                }
            }
        }

        /// <summary>
        /// 释放SFTP连接
        /// </summary>
        public void Dispose()
        {
            this.DisconnectSFTP();
            this._channel = null;
            this._session = null;
            this._channelSftp = null;
        }

        /// <summary>
        /// 目录或文件是否存在
        /// </summary>
        /// <param name="objectPath">
        /// 相对默认操作目录下的目录
        /// eg:/Directory ;
        /// eg:/Directory/File.txt
        /// </param>
        /// <returns></returns>
        public bool DirectoryOrFileExist(string objectPath)
        {
            try
            {
                objectPath = objectPath.Replace('\\', '/');
                _channelSftp.ls(_defaultfRemotePath + objectPath.TrimStart('/'));
                return true;
            }
            catch (SftpException)
            {
                return false;//执行ls命令时出错，则目录不存在。
            }
        }

        /// <summary>
        /// SFTP获取文件
        /// </summary>
        /// <param name="remoteFileName">远程文件名</param>
        /// <param name="remotePath">
        /// 相对默认操作目录下的文件路径(不含文件名)
        /// eg:/Directory/Directory/
        /// </param>
        /// <param name="localFileName">本地文件名</param>
        /// <param name="localPath">
        /// 本地文件路径(不含文件名)
        /// eg:D:/Directory/Directory/
        /// </param>
        /// <returns></returns>
        public bool Get(string remoteFileName, string remotePath, string localFileName, string localPath)
        {
            try
            {
                //SFTP上完整的文件路径
                string FullRemotePath = _defaultfRemotePath + remotePath.Replace('\\', '/').Trim('/') + "/" + remoteFileName;

                //本地完整文件路径
                string FullLocalPath = localPath.Replace('\\', '/').Trim('/') + "/" + localFileName;

                //判断本地文件目录路径是否存在，不存在则创建文件夹 
                if (!Directory.Exists(localPath))
                {
                    //不存在就创建目录 
                    Directory.CreateDirectory(localPath);
                }
                //判断本地文件是否存在 
                if (File.Exists(FullLocalPath))
                {
                    _log.Error("本地已存在同名文件！");
                    return false;
                }
                //判断服务器上文件是否存在
                if (!DirectoryOrFileExist(FullRemotePath))
                {
                    _log.Error(string.Format("服务器上不存在目录文件:{0}！", FullRemotePath));
                    return false;
                }
                //获取指定文件
                _channelSftp.get(FullRemotePath, FullLocalPath);
                return true;
            }
            catch (Exception e)
            {
                _log.Error("SFTP获取文件失败！", e);
                return false;
            }
        }

        /// <summary>
        /// SFTP获取文件
        /// </summary>
        /// <param name="remoteFileName">远程文件名</param>
        /// <param name="localFileName">本地文件名</param>
        /// <param name="localPath">
        /// 本地文件路径(不含文件名)
        /// eg:D:/Directory/Directory/
        /// </param>
        /// <returns></returns>
        public bool Get(string remoteFileName, string localFileName, string localPath)
        {
            return Get(remoteFileName, DirectoryPath, localFileName, localPath);
        }

        /// <summary>
        /// SFTP获取文件
        /// </summary>
        /// <param name="fileName">文件名</param>
        /// <param name="localPath"> 本地文件路径(不含文件名) eg:D:/Directory/Directory/</param>
        /// <returns></returns>
        public bool Get(string fileName, string localPath)
        {
            return Get(fileName, fileName, localPath);
        }

        /// <summary>
        /// SFTP存放文件
        /// </summary>
        /// <param name="remoteFileName">远程文件名</param>
        /// <param name="remotePath">
        /// 相对默认操作目录下的文件路径(不含文件名)
        /// eg:/Directory/Directory/
        /// </param>
        /// <param name="localFileName">本地文件名</param>
        /// <param name="localPath">
        /// 本地文件路径(不含文件名)
        /// eg:D:/Directory/Directory/
        /// </param>
        /// <returns></returns>
        public bool Put(string remoteFileName, string remotePath, string localFileName, string localPath)
        {
            try
            {
                //SFTP上完整的文件路径
                string FullRemotePath = _defaultfRemotePath + remotePath.Replace('\\', '/').Trim('/') + "/" + remoteFileName;

                //本地完整文件路径
                string FullLocalPath = localPath.Replace('\\', '/').Trim('/') + "/" + localFileName;

                //是否有相对文件目录,不存在则创建
                if (!DirectoryOrFileExist(remotePath))
                {
                    CreateDirectorys(remotePath);
                }
                //检查本地是否存在文件
                if (!File.Exists(FullLocalPath))
                {
                    _log.Error(string.Format("本地不存在文件:{0}！", FullLocalPath));
                    return false;
                }
                //判断服务器上是否已存在文件
                if (DirectoryOrFileExist(FullRemotePath))
                {
                    _log.Error("服务器上已存在指定文件或目录！");
                    return false;
                }

                //上传文件
                _channelSftp.put(FullLocalPath, FullRemotePath);
                return true;
            }
            catch (Exception e)
            {
                _log.Error("SFTP存放文件失败！", e);
                return false;
            }
        }

        /// <summary>
        /// SFTP存放文件
        /// </summary>
        /// <param name="remoteFileName">远程文件名</param>
        /// <param name="localFileName">本地文件名</param>
        /// <param name="localPath">
        /// 本地文件路径(不含文件名)
        /// eg:D:/Directory/Directory/
        /// </param>
        /// <returns></returns>
        public bool Put(string remoteFileName, string localFileName, string localPath)
        {
            return Put(remoteFileName, DirectoryPath, localFileName, localPath);
        }

        /// <summary>
        /// SFTP存放文件
        /// </summary>
        /// <param name="fileName">文件名</param>
        /// <param name="localPath"> 本地文件路径(不含文件名) eg:D:/Directory/Directory/</param>
        /// <returns></returns>
        public bool Put(string fileName, string localPath)
        {
            return Put(fileName, fileName, localPath);
        }

        /// <summary>
        /// 删除SFTP上的文件
        /// </summary>
        /// <param name="remotePath">
        /// 相对默认操作目录下的文件路径(不含文件名)
        /// eg:/Directory/Directory/
        /// </param>
        /// <param name="remoteFileName">远程文件名</param>
        /// <returns></returns>
        public bool Delete(string remotePath, string remoteFileName)
        {
            try
            {
                //SFTP上完整的文件路径
                string FullRemotePath = _defaultfRemotePath + remotePath.Replace('\\', '/').Trim('/') + "/" + remoteFileName;

                if (DirectoryOrFileExist(FullRemotePath))
                {
                    _channelSftp.rm(FullRemotePath);
                }
                else
                {
                    _log.Error("不存在文件：" + FullRemotePath);
                    return false;
                }

                return true;
            }
            catch (Exception e)
            {
                _log.Error("SFTP删除文件失败！", e);
                return false;
            }
        }

        /// <summary>
        /// 删除SFTP上的文件
        /// </summary>
        /// <param name="fileName">远程文件名</param>
        /// <returns></returns>
        public bool Delete(string fileName)
        {
            return Delete(DirectoryPath, fileName);
        }

        #endregion

        #region Private Method

        /// <summary>
        /// 判断指定端口是否有效
        /// </summary>
        /// <returns></returns>
        private bool PortIsOpen()
        {
            try
            {

                IPAddress IP = IPAddress.Parse(_hostName);
                IPEndPoint EndPort = new IPEndPoint(IP, _port);
                TcpClient Tcp = new TcpClient();
                
                //连接到远程主机
                Tcp.Connect(IP, _port);

                //判断是否连上对方主机
                bool Result = Tcp.Connected;

                //释放连接
                Tcp.Close();

                return Result;
            }
            catch (Exception e)
            {
                _log.Error("连接SFTP服务时，打开指定端口时出错！", e);
                return false;
            }
        }

        /// <summary>
        /// 通过私钥建立SFTP连接
        /// </summary>
        private void ConnectSFTPByPrivateKey()
        {
            try
            {
                JSch JSchModel = new JSch();
                JSchModel.addIdentity(_privateKeyFile, _passphrase);
                _session = JSchModel.getSession(_userName, _hostName, _port);
                _session.setUserInfo(new SFTPUserInfoModel());

                CreateConnectSFTP();
            }
            catch (Exception e)
            {
                _log.Warn("通过私钥建立SFTP连接失败！", e);
            }
        }

        /// <summary>
        /// 通过密码建立SFTP连接
        /// </summary>
        private void ConnectSFTPByPassword()
        {
            try
            {
                JSch JSchModel = new JSch();
                SFTPUserInfoModel User = new SFTPUserInfoModel();
                _session = JSchModel.getSession(_userName, _hostName, _port);
                _session.setPassword(_password);
                User.setPassword(_password);
                _session.setUserInfo(User);

                CreateConnectSFTP();
            }
            catch (Exception e)
            {
                _log.Warn("通过密码建立SFTP连接失败！", e);
            }
        }

        /// <summary>
        /// 建立SFTP连接
        /// </summary>
        private void CreateConnectSFTP()
        {
            try
            {
                _session.connect();
                _channel = _session.openChannel("sftp");
                _channel.connect();
                _channelSftp = (ChannelSftp)_channel;
            }
            catch 
            {
                throw;
            }
        }

        /// <summary>
        /// 断开SFTP    
        /// </summary>
        private void DisconnectSFTP()
        {
            try
            {
                if (_session != null)
                {
                    if (_session.isConnected())
                    {
                        _channel.disconnect();
                        _session.disconnect();
                    }
                }
            }
            catch (Exception e)
            {
                _log.Error("断开SFTP失败！", e);
            }
        }

        /// <summary>
        /// 创建多级目录
        /// </summary>
        /// <param name="fileDirectory">
        /// 多级目录（相对根目录的完整目录）
        /// eg:/Directory/Directory/
        /// </param>
        private bool CreateDirectorys(string fileDirectory)
        {
            //目录截止索引
            int Index = 0;
            //目录名称
            string DirectoryName = string.Empty;
            //多级目录字符串
            string DirectoryString = fileDirectory.Replace('\\', '/').Trim('/');
            //目录路径
            string DirectoryPath = string.Empty;
            //目录的个数
            int DirectoryCount = DirectoryString.Split('/').Length;

            try
            {
                for (int i = 0; i < DirectoryCount; i++)
                {
                    Index = DirectoryString.IndexOf('/');
                    if (Index > 0)
                    {
                        DirectoryName = DirectoryString.Substring(0, Index);
                        //多级目录字符串截掉当前目录
                        DirectoryString = DirectoryString.Substring(Index + 1, DirectoryString.Length - Index - 1);
                    }
                    else
                    {
                        DirectoryName = DirectoryString;
                    }

                    //目录路径加上当前目录
                    DirectoryPath = DirectoryPath + "/" + DirectoryName;

                    //判断该目录路径是否存在，不存在则创建
                    if (!DirectoryOrFileExist(DirectoryPath))
                    {
                        _channelSftp.mkdir(_defaultfRemotePath + DirectoryPath.TrimStart('/'));
                    }
                }
                return true;
            }
            catch (Exception e)
            {
                _log.Error(string.Format("创建目录:{0} 失败！", fileDirectory), e);
                return false;
            }
        }

        #endregion
    }
}
