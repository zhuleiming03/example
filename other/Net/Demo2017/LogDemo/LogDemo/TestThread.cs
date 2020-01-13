using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace LogDemo
{
    class TestThread
    {
        public void Begin()
        {
            DemoOne demoA = new DemoOne();

            demoA.demo1(1);

            demoA.demo3();

            //DemoTwo demoB = new DemoTwo();

            //demoB.demo1(2);

            Thread.Sleep(1000);
        }
    }
}
