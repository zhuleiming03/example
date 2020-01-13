using log4net;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Reflection;
using System.Globalization;

namespace ConsoleDemo
{
    public class PlanTaskTest
    {
        public static void Test(string[] args)
        {
            ILog logger = LogManager.GetLogger(MethodBase.GetCurrentMethod().DeclaringType);

            DateTime replytime = DateTime.Now;

            if (args.Length > 0)
            {
                logger.Info(string.Format("程序传入{0}个参数，第一个参数的值为：{1}", args.Length, args[0].ToString()));
                DateTime.TryParseExact(args[0], "yyyy-MM-dd", CultureInfo.CurrentCulture, DateTimeStyles.None, out replytime);
                if (replytime == DateTime.MinValue)
                    replytime = DateTime.Now;
            }

            //消息
            logger.Info("执行回盘程序日期：" + replytime.ToString("yyyyMMdd"));
        }
    }
}
