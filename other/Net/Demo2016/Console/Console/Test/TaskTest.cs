using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace ConsoleDemo
{
    public class TaskTest
    {
        private int _Amount;

        public void Test()
        {
            _Amount = 0;

            //超时次数
            int MaxCount = 5;
            //最大次数
            int Count = 0;
            //获取结果
            bool Result = false;
            //获取失败后再次获取文件的间隔时间
            int TimeGap = 1000;

            Console.WriteLine("程序开始");

            //获取失败且未超过最大获取次数时继续获取文件
            while (!Result && Count < MaxCount)
            {
                //次数累加
                Count++;

                //获取文件
                var task = Task.Factory.StartNew<bool>(() => Add());
                Result = task.Result;

                Console.WriteLine(string.Format("获取文件状态：{0} 和次数：{1}", Result, Count));

                //获取失败则休眠一段时间
                if (!Result)
                {
                    task.Wait(TimeGap);
                }
            }

            if (Result)
            {
                Console.WriteLine("获取文件成功，将执行回盘操作");
            }
            else
            {
                Console.WriteLine("获取文件失败，请联系晋城银行");
            }
        }

        /// <summary>
        /// 模拟获取文件事件
        /// </summary>
        /// <returns></returns>
        private bool Add()
        {
            _Amount++;
            Thread.Sleep(3000);
            if (_Amount > 3)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }
}
