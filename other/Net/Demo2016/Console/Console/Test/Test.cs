using Cn.Vcredit.VBS.PostLoan.OrderInfo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using com.Vcredit.OnlineProduct.Base.BLL;

namespace ConsoleDemo
{
    public class TestDemo : AdvCloanBase
    {
        public void Main()
        {
            Business bus = new Business();
            bus.BusinessID = 3169770;
            bus.LoanCapital = 300m;
            bus.IsByStages = false;
            bus.LoanTime = DateTime.Parse("2017-01-18 12:22:56.000");
            bus.ProductType = 17;
            bus.DayRate = 0.000300m;
            bus.DayServiceRate = 0.000300m;
            bus.ProceduresAmout = 10m;
            bus.InterestRate = 0.009500m;
            bus.ServiceRate = 0.002000m;

            List<BillItem> aa = GetSurfingAdvBillItem(bus);

            int i = aa.Count();
        }

        /// <summary>
        /// 得到翼支付提前清贷科目列表
        /// </summary>
        /// <param name="bus">订单信息</param>
        /// <returns>提前清贷科目列表</returns>
        public List<BillItem> GetSurfingAdvBillItem(Business bus)
        {
            List<BillItem> billItemList = new List<BillItem>();
            if (bus.Bills == null)
                bus.Bills = new List<Bill>();

            //是否出了手续费
            bool existsProceduresAmout = false;
            //未出剩余本金
            decimal residualCapital = bus.LoanCapital;
            //未还剩余本金
            decimal dueCapital = bus.LoanCapital;

            //已出账单数量
            int hasMonthBill = 0;
            foreach (Bill bill in bus.Bills)
            {
                if (bill.BillType != (byte)EnumBillKind.Normal && bill.BillType != (byte)EnumBillKind.Guarantee)
                    continue;

                if (bill.IsShelve)
                    continue;

                hasMonthBill = hasMonthBill + 1;
                if (bill.BillItems == null)
                    bill.BillItems = new List<BillItem>();

                if (bill.BillItems.Exists(o => o.Subject == (byte)EnumCostSubject.Procedures))
                    existsProceduresAmout = true;

                BillItem capitalItem = bill.BillItems.FirstOrDefault(o => o.Subject == (byte)EnumCostSubject.Capital);
                if (capitalItem != null)
                {
                    residualCapital = residualCapital - capitalItem.Amount;
                    dueCapital = dueCapital - capitalItem.ReceivedAmt;
                }
            }
            //手续费
            if (!existsProceduresAmout)
                billItemList.Add(GetBillItem((byte)EnumCostSubject.Procedures, bus.ProceduresAmout, bus.BusinessID));

            DateTime lastBillDay;

            if (hasMonthBill == 0)
                lastBillDay = bus.LoanTime.Date;
            else
                lastBillDay = bus.RelativeDate.AddMonths(hasMonthBill);

            int daySpan = (DateTime.Now.Date - lastBillDay.Date).Days + 1;
            if (daySpan > 0)
            {
                if (bus.IsByStages.HasValue && bus.IsByStages.Value)
                {
                    //加收利息
                    decimal addInterestAmount = Math.Round(bus.LoanCapital * daySpan * bus.InterestRate / 30, 2, MidpointRounding.AwayFromZero);
                    if (addInterestAmount > 0)
                        billItemList.Add(GetBillItem((byte)EnumCostSubject.AddInterest, addInterestAmount, bus.BusinessID));

                    //加收服务费
                    decimal addServiceAmount = Math.Round(bus.LoanCapital * daySpan * bus.ServiceRate / 30, 2, MidpointRounding.AwayFromZero);
                    if (addServiceAmount > 0)
                        billItemList.Add(GetBillItem((byte)EnumCostSubject.AddServiceFee, addServiceAmount, bus.BusinessID));
                }
                else
                {
                    //如果是放款日同一天
                    if (DateTime.Now.Date == bus.LoanTime.Date)
                        daySpan = 2;

                    if (bus.ProductType == 14)
                    {
                        if (bus.DayRate.HasValue)
                        {
                            //不分期，老订单综合费用
                            decimal compositeFee = Math.Round(bus.LoanCapital * (daySpan - 1) * bus.DayRate.Value, 2, MidpointRounding.AwayFromZero);
                            if (compositeFee > 0)
                                billItemList.Add(GetBillItem((byte)EnumCostSubject.CompositeFee, compositeFee, bus.BusinessID));
                        }
                    }
                    else
                    {
                        if (bus.DayRate.HasValue)
                        {
                            //不分期，新的是加收利息、加收服务费
                            decimal addInterestAmount = Math.Round(bus.LoanCapital * (daySpan - 1) * bus.DayRate.Value, 2, MidpointRounding.AwayFromZero);
                            if (addInterestAmount > 0)
                                billItemList.Add(GetBillItem((byte)EnumCostSubject.AddInterest, addInterestAmount, bus.BusinessID));
                        }
                        if (bus.DayServiceRate.HasValue)
                        {
                            decimal addServiceAmount = Math.Round(bus.LoanCapital * (daySpan - 1) * bus.DayServiceRate.Value, 2, MidpointRounding.AwayFromZero);
                            if (addServiceAmount > 0)
                                billItemList.Add(GetBillItem((byte)EnumCostSubject.AddServiceFee, addServiceAmount, bus.BusinessID));
                        }
                    }
                }
            }
            if (residualCapital > 0)
            {
                //剩余本金
                billItemList.Add(GetBillItem((byte)EnumCostSubject.ResidualCapital, residualCapital, bus.BusinessID));

                if (bus.IsByStages.HasValue && bus.IsByStages.Value)
                {
                    //清贷服务费
                    decimal serviceAmount = Math.Round(residualCapital * 0.02m, 2, MidpointRounding.AwayFromZero);
                    billItemList.Add(GetBillItem((byte)EnumCostSubject.AdvServiceFee, serviceAmount, bus.BusinessID));
                }
            }

            return billItemList;
        }
    }
}
