using System;
using log4net;
using System.Reflection;
using Server;
using System.Configuration;
using System.Xml.Serialization;
using System.IO;
using System.Text;
using System.Collections.Generic;
using System.Globalization;
using System.Threading;
using ConsoleDemo.Test;

namespace ConsoleDemo
{
    class Program
    {
        static void Main(string[] args)
        {
            //LogMessageTest.Test();
            //SFTPTest.Test();
            //XMLTest.Test();
            //new TaskTest().Test();
            //PlanTaskTest.Test(args);
            //JObjectTest.Test();
            //new TestDemo().Main();
            //EmailTest.Test();
            //ExcelTest.Test1();
            //Qunar.SyncTest();
            //Qunar.Query();
            //PythonTest.test2();
            //TestUnit.Test();
            //CallVFP.Test();
            //new OutOfMemoryExceptionTest().Test();
            //DateTimeTest.TimeCompareTest();

            //Desk desk1 = new Desk() { Info = "Steven", StudentCode = 10 };
            //Desk desk2 = new Desk() { Info = "Sean", StudentCode = 99 };

            //Student a1 = new Student();
            //a1.Name = "Steven";
            //a1.desk = desk1;


            //Student b1 = new Student();
            //b1.Name = "Sean";
            //b1.desk = desk2;

            //aaa(desk1, desk2);

            //int aa = a1.desk.StudentCode;   //Steven   10
            //int bb = b1.desk.StudentCode;   //Sean     0

            //Desk result = a1.desk;

            Console.Write(Math.Floor(15.567*100)/100);
            Console.ReadLine();
               
        }

        public static void aaa(Desk a, Desk b)
        {
            Desk tmp = a;
            a = b;
            b = tmp;
            a.StudentCode = 0;
        }

    }


}
