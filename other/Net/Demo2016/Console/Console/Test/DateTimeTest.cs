using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleDemo.Test
{
    public static class DateTimeTest
    {
        public static void TimeCompareTest()
        {
            DateTime now = DateTime.Now;

            DateTime begin = new DateTime(2019, 1, 3, 01, 30, 0);

            DateTime end = new DateTime(2019, 1, 3, 23, 30, 0);

            Console.WriteLine("当期日期：" + now);
            Console.WriteLine("开始日期：" + begin);
            Console.WriteLine("结束日期：" + end);

            DateTime begin11 = new DateTime(2019, 2, 3, 01, 30, 0);
            TimeSpan beginTime = begin.TimeOfDay;
            TimeSpan begin11Time = begin11.TimeOfDay;
            TimeSpan endTime = end.TimeOfDay;
            Console.WriteLine("开始时间：" + beginTime);
            Console.WriteLine("开始11时间：" + begin11Time);
            Console.WriteLine("结束时间：" + endTime);

            Console.WriteLine(now.TimeOfDay.CompareTo(beginTime));
            Console.WriteLine(now.TimeOfDay.CompareTo(endTime));
            Console.WriteLine(begin11Time.CompareTo(beginTime));

            DateTime? date1 = null;
            if (date1.HasValue)
            {
                Console.WriteLine("date1 HasValue");
            }
            DateTime? date2 = default(DateTime);
            if (date2.HasValue)
            {
                Console.WriteLine("date2 HasValue");
            }
            DateTime? date3 = begin;
            if (date3.HasValue)
            {
                Console.WriteLine("date3 HasValue");
            }

            Console.ReadLine();
        }
    }
}
