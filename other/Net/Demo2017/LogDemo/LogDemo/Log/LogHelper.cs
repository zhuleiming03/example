using System;
using System.IO;

namespace LogDemo
{
    public class LogHelper
    {
        private static readonly string LogPath = AppDomain.CurrentDomain.BaseDirectory + "log\\";
        private static readonly object lockHelper = new object();

        public void Track(string log)
        {
            lock (lockHelper)
            {
                string logPath = Environment.CurrentDirectory + "\\Log\\" + DateTime.Now.ToString("yyyy-MM-dd") + "\\Detail\\";
                if (!Directory.Exists(logPath))
                {
                    Directory.CreateDirectory(logPath);
                }
                string msg = DateTime.Now + "  " + log + "\r\n";
                File.AppendAllText(logPath + DateTime.Now.ToString("HH") + "Hour.txt", msg);
            }
        }
    }
}
