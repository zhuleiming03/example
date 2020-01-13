using log4net;
using System;
using System.Reflection;

namespace ConsoleDemo
{
    public static class LogMessageTest
    {
        public static void Test()
        {
            //------------------------------------------- 相关配置 ---------------------------------------//
            //log4net.config》属性》复制到输出目录》如果较新则复制
            //AssemblyInfo.cs 新增 [assembly: XmlConfigurator(ConfigFile = "log4net.config", Watch = true)]
            //------------------------------------------- 相关配置 ---------------------------------------//

            ILog logger = LogManager.GetLogger(MethodBase.GetCurrentMethod().DeclaringType);

            //消息
            logger.Info("这是个提示");

            //警告
            string warn = string.Empty;
            logger.Warn("字符串未赋值");

            //异常
            try
            {
                string a = "a1";
                int b = int.Parse(a);
            }
            catch (Exception e)
            {
                logger.Error("转换失败", e);
            }

            //错误
            try
            {
                string a = "a1";
                int b = int.Parse(a);
            }
            catch (Exception e)
            {
                logger.Fatal("未知错误", e);
            }
        }
    }
}
