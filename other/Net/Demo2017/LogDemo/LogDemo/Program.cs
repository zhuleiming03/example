using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace LogDemo
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("测试开始！");

            TestThread demo = new TestThread();

            Thread thread = new Thread(demo.Begin);
            thread.Start();
        }

        
    }
}
