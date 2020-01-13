using log4net;
using Server;
using System.Reflection;

namespace ConsoleDemo
{
    public class SFTPTest
    {
        public static void Test()
        {
            //初始化日志对象
            ILog _log = LogManager.GetLogger(MethodBase.GetCurrentMethod().DeclaringType);

            SFTPServer Sftp = new SFTPServer();
            Sftp.ConnectSFTP();

            _log.Debug("SFTP连接状态：" + Sftp.IsConnected);

            //77服务器存取文件
            //Sftp.DirectoryPath = "/jcptftp/out_files/";
            //42服务器存取文件
            //Sftp.DirectoryPath = "/JCBank/Deduct/";
            //bool Result = false;
            //Result = Sftp.Put("Client.txt", "D:/SFTP");
            //Result = Sftp.Get("Server.txt", "D:/SFTP");
            //_log.Debug("存取文件：" + Result);

            Sftp.Dispose();
        }
    }
}
