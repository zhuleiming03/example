using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogDemo
{
    [AttributeUsage(AttributeTargets.Method, AllowMultiple = true)]
    public class TrackMethodAttribute : Attribute
    {
        public TrackMethodAttribute()
        {
            //Console.WriteLine("LogMethodAttribute...构造函数");
        }
    }
}
