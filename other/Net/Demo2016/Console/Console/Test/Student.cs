using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleDemo
{
    public class Student
    {
        public Student()
        {
            _desk = new Desk();
        }

        public Desk _desk;

        public string Name { get; set; }


        public Desk desk { get; set; }
    }

    public class Desk
    {
        public string Info { get; set; }

        public int StudentCode { get; set; }

    }
}
