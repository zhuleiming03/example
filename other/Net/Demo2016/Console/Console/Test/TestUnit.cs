using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace ConsoleDemo
{
    public class TestUnit
    {
        private static int WaitingDataTimeMin = 30;

        public static void Test()
        {
            Thread t1 = new Thread(new TestUnit().DataAlter);
            t1.Start();
        }

        public void DataAlter()
        {
            DateTime Now = DateTime.Now;
            Console.WriteLine(Now.ToString());
            Now.AddMinutes(WaitingDataTimeMin);
            Console.WriteLine(Now.ToString());
            Console.ReadLine();
        }
    }
}
