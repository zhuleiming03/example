using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogDemo
{
    [Track]
    public class DemoOne : ContextBoundObject
    {

        [TrackMethod]
        public int demo1(int a)
        {
            demo2("zlm");
            return ++a;
        }

        [TrackMethod]
        public string demo2(string str)
        {
            List<int> aa = new List<int>();
            for (int i = 0; i < 5; i++)
            {
                aa.Add(i * 2);
            }

            return string.Format("This string is :{0}", str);
        }

        [TrackMethod]
        public int demo3()
        {
            DemoTwo demoB = new DemoTwo();

            return demoB.demo1(2);
        }

        [TrackMethod]
        public List<BillItem> demo4(Bill bill)
        {
            return bill.BillItems;
        }

        [TrackMethod]
        public void demo5()
        {
            demo1(1);

            demo2("zlm");

            List<int> aa = new List<int>();
            for (int i = 0; i < 5; i++)
            {
                aa.Add(i * 2);
            }

            demo4(GetBill());
        }

        public static Bill GetBill()
        {
            Bill bill = new Bill();
            bill.BillID = 11;
            bill.IsDelete = false;
            bill.Remark = "测试";

            List<BillItem> list = new List<BillItem>();
            for (int i = 0; i < 3; i++)
            {
                BillItem item = new BillItem();
                item.BillID = bill.BillID;
                item.BillItemID = i;
                item.Amount = i * 1.2f;
                list.Add(item);
            }
            bill.BillItems = list;

            return bill;
        }
    }

    public class Bill
    {
        public int BillID { get; set; }

        public bool IsDelete { get; set; }

        public string Remark { get; set; }

        public List<BillItem> BillItems { get; set; }
    }

    public class BillItem
    {
        public int BillID { get; set; }

        public int BillItemID { get; set; }

        public float Amount { get; set; }
    }
}
