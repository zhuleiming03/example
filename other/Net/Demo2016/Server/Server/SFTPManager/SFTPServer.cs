using log4net;
using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Configuration;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Reflection;
using System.Text;
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
        /// 默认操作目录（默认为根目录"/"）
        /// </summary>
        private string _defaultfRemotePath;

        /// <summary>
        /// 私钥文件存放路径
        /// </summary>
        private string _privateKeyFile;

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
        }

        #region Public Method

        /// <summary>
        /// 建立SFTP连接
        /// </summary>
        /// <param name="type"></param>
        public void ConnectSFTP(SFTPConnectTypeEnum type = SFTPConnectTypeEnum.Default)
        {
            if (PortIsOpen())
            {
                try
                {
                    switch (type)
                    {
                        case SFTPConnectTypeEnum.Password:
                            ConnectSFTPByPassword();
                            break;
                        case SFTPConnectTypeEnum.PrivateKey:
                            ConnectSFTPByPrivateKey();
                            break;
                        case SFTPConnectTypeEnum.Default:
                            ConnectSFTPByPassword();
                            if (!IsConnected)
                            {
                                ConnectSFTPByPrivateKey();
                            }
                            break;
                        default:
                            break;
                    }
                }
                catch (Exception e)
                {
                    _log.Error("连接SFTP失败！", e);
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

        #endregion

        #region Private Method

        /// <summary>
        /// 判断指定端口是否打开
        /// </summary>
        /// <returns></returns>
        private bool PortIsOpen()
        {
            try
            {
                IPAddress IP = IPAddress.Parse(_hostName);
                IPEndPoint TestPort = new IPEndPoint(IP, _port);
                TcpClient Tcp = new TcpClient();
                Tcp.Connect(IP, _port);
                return true;
            }
            catch (NotSupportedException e)
            {
                _log.Warn(e.Message, e);
                return false;
            }
            catch (Exception e)
            {
                _log.Warn("连接SFTP服务时，打开指定端口时出错！", e);
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
                _session = JSchModel.getSession(_userName, _hostName, _port);
                _session.setUserInfo(new SFTPUserInfoModel());
                CreateConnectSFTP();
            }
            catch (Exception e)
            {
                _log.Error("通过私钥建立SFTP连接失败！", e);
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
                _session.setHost(_hostName);
                _session.setPassword(_password);
                User.setPassword(_password);
                _session.setUserInfo(User);
                CreateConnectSFTP();
            }
            catch (Exception e)
            {
                _log.Error("通过密码建立SFTP连接失败！", e);
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
            catch (Exception e)
            {
                _log.Error("连接SFTP失败！", e);
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
                _log.Warn("断开SFTP失败！" + e.Message);
            }
        }

        #endregion

        #region 公共方法

        /// <summary>
        /// SFTP获取文件   
        /// </summary>
        /// <param name="remotePath">
        /// 相对默认操作目录下的文件路径(含文件名)
        /// eg:/Directory/Directory/File.txt
        /// </param>
        /// <param name="localPath">
        /// 本地文件路径(含文件名)
        /// eg:D:/Directory/Directory/File.txt
        /// </param>
        /// <returns></returns>
        //internal bool Get(string remotePath, string localPath)
        //{
        //    try
        //    {
        //        //SFTP上的文件路径
        //        string FullRemotePath = DefaultfRemotePath + remotePath.TrimStart('/');
        //        Tamir.SharpSsh.java.String Source = new Tamir.SharpSsh.java.String(FullRemotePath);

        //        //本地地址
        //        Tamir.SharpSsh.java.String Destination = new Tamir.SharpSsh.java.String(localPath);

        //        string FilePath = localPath.Substring(0, localPath.LastIndexOf('/'));

        //        //判断文件路径是否存在，不存在则创建文件夹 
        //        if (!Directory.Exists(FilePath))
        //        {
        //            Directory.CreateDirectory(FilePath);//不存在就创建目录 
        //        }
        //        //判断文件是否存在 
        //        if (File.Exists(localPath))
        //        {
        //            return false;
        //        }
        //        else
        //        {
        //            _sftp.get(Source, Destination);
        //            return true;
        //        }
        //    }
        //    catch (Exception e)
        //    {
        //        Logger.Error("SFTP获取文件失败！详情：" + e.Message);
        //        return false;
        //    }
        //}

        /// <summary>
        /// SFTP存放文件   
        /// </summary>
        /// <param name="localPath">
        /// 本地文件路径(含文件名)
        /// eg:D:/Directory/Directory/File.txt
        /// </param>
        /// <param name="remotePath">
        /// 相对默认操作目录下的文件路径(含文件名)
        /// eg:/Directory/Directory/File.txt
        /// </param>
        //internal bool Put(string localPath, string remotePath)
        //{
        //    try
        //    {
        //        //SFTP上的文件路径
        //        string FullRemotePath = DefaultfRemotePath + remotePath;

        //        //是否有相对文件目录
        //        if (remotePath.LastIndexOf('/') > 0)
        //        {
        //            //相对目录
        //            string FileDirectory = remotePath.Substring(0, remotePath.LastIndexOf('/'));
        //            //不存在则创建
        //            if (!DirectoryOrFileExist(FileDirectory))
        //            {
        //                CreateDirectorys(FileDirectory);
        //            }
        //        }

        //        //本地地址
        //        Tamir.SharpSsh.java.String Source = new Tamir.SharpSsh.java.String(localPath);
        //        //上传地址
        //        Tamir.SharpSsh.java.String Destination = new Tamir.SharpSsh.java.String(FullRemotePath);

        //        //不存在文件时上传文件
        //        if (!DirectoryOrFileExist(remotePath))
        //        {
        //            _sftp.put(Source, Destination);
        //        }
        //        return true;
        //    }
        //    catch (Exception e)
        //    {
        //        Logger.Error("SFTP存放文件失败！详情：" + e.Message);
        //        return false;
        //    }
        //}

        /// <summary>
        /// 目录或文件是否存在
        /// </summary>
        /// <param name="objectPath">
        /// 相对默认操作目录下的目录
        /// eg:/Directory ;
        /// eg:/Directory/File.txt
        /// </param>
        /// <returns></returns>
        //internal bool DirectoryOrFileExist(string objectPath)
        //{
        //    try
        //    {
        //        _sftp.ls(DefaultfRemotePath + objectPath.TrimStart('/'));
        //        return true;
        //    }
        //    catch (SftpException)
        //    {
        //        return false;//执行ls命令时出错，则目录不存在。
        //    }
        //}



        #endregion

        #region 私有方法

        /// <summary>
        /// 创建单个目录
        /// </summary>
        /// <param name="dirName">
        /// 相对默认操作目录下的目录
        /// eg:/Directory;
        /// eg:/Directory/Directory;
        /// </param>
        //private void CreateDirectory(string directoryName)
        //{
        //    Vector VectorModel = _sftp.ls(DefaultfRemotePath);
        //    foreach (ChannelSftp.LsEntry FileName in VectorModel)
        //    {
        //        string Name = FileName.getFilename();
        //        if (Name == directoryName)
        //        {
        //            throw new Exception("dir is exist");
        //        }
        //    }
        //    _sftp.mkdir(DefaultfRemotePath + directoryName.TrimStart('/'));
        //}

        /// <summary>
        /// 创建多级目录
        /// </summary>
        /// <param name="fileDirectory">
        /// 多级目录
        /// eg:/Directory/Directory
        /// </param>
        //private void CreateDirectorys(string fileDirectory)
        //{
        //    //目录截止索引
        //    int Index = 0;
        //    //目录名称
        //    string DirectoryName = string.Empty;
        //    //多级目录字符串
        //    string DirectoryString = fileDirectory.Trim('/');
        //    //目录路径
        //    string DirectoryPath = string.Empty;
        //    //目录的个数
        //    int DirectoryCount = DirectoryString.Split('/').Length;

        //    try
        //    {
        //        for (int i = 0; i < DirectoryCount; i++)
        //        {
        //            Index = DirectoryString.IndexOf('/');
        //            if (Index > 0)
        //            {
        //                DirectoryName = DirectoryString.Substring(0, Index);
        //                //多级目录字符串截掉当前目录
        //                DirectoryString = DirectoryString.Substring(Index + 1, DirectoryString.Length - Index - 1);
        //            }
        //            else
        //            {
        //                DirectoryName = DirectoryString;
        //            }

        //            //目录路径加上当前目录
        //            DirectoryPath = DirectoryPath + "/" + DirectoryName;

        //            //判断该目录路径是否存在，不存在则创建
        //            if (!DirectoryOrFileExist(DirectoryPath))
        //            {
        //                CreateDirectory(DirectoryPath);
        //            }
        //        }
        //    }
        //    catch (Exception e)
        //    {
        //        Logger.Error("创建目录失败！详情：" + e.Message);
        //    }
        //}



        #endregion

    }
}
