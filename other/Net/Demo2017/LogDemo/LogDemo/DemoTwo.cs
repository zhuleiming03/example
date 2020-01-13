using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogDemo
{
    [Track]
    public class DemoTwo: ContextBoundObject
    {
        [TrackMethod]
        public int demo1(int a)
        {
            return a * 3;
        }
    }
}
