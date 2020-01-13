//using Microsoft.Office.Interop.Excel;
using Aspose.Cells;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Text;
using Server;
using log4net;
using System.Reflection;
using System.Configuration;

namespace ConsoleDemo
{
    public static class ExcelTest
    {
        public static void Test()
        {
            try
            {
                Workbook book = new Workbook();

                Worksheet cellSheet = book.Worksheets[0];

                Cells cells = cellSheet.Cells;

                Console.WriteLine("开始查询");

                DataTable dt = GetDataTable();

                Console.WriteLine("开始生成Excel");

                cells.ImportDataTable(dt, false, 0, 0);

                cellSheet.AutoFitColumns();                

                Console.WriteLine("保存Excel");

                book.Save(@"D:\Demo2016\Console\Console\Excel\test.xlsx");

                Console.WriteLine("保存成功");
            }
            catch(Exception e)
            {
                Console.WriteLine(e.ToString());
                Console.ReadLine();
            }           
        }

        public static void Test1()
        {
            try
            {
                string TempleUrl = ConfigurationManager.AppSettings["FTPExcelTemple"].ToString();

                Workbook book = new Workbook(TempleUrl);

                Worksheet cellSheet = book.Worksheets[0];

                Cells cells = cellSheet.Cells;

                Console.WriteLine("开始查询");

                DataTable dt = GetDataTable1();

                ILog logger = LogManager.GetLogger(MethodBase.GetCurrentMethod().DeclaringType);

                logger.Info("获取数据行数："+dt.Rows.Count);

                Console.WriteLine("开始生成Excel");

                cells.ImportDataTable(dt, false, 1, 0);

                Console.WriteLine("保存Excel");

                logger.Info("保存Excel成功");

             
                string SaveUrl = ConfigurationManager.AppSettings["FTPSaveFile"].ToString();

                string Url = string.Format(@"{0}{1}逾期静态表.xlsx", SaveUrl, DateTime.Now.ToString("yyyyMMdd"));

                logger.Info(Url);

                book.Save(Url);

                Console.WriteLine("保存成功");
            }
            catch (Exception e)
            {
                Console.WriteLine(e.ToString());
                Console.ReadLine();
            }
        }

        public static DataTable GetDataTable()
        {
            string ConnectString = @"Data Source=.;Initial Catalog=PostLoan;Persist Security Info=True;User ID=;Password=";
            string QueryText = "SELECT * FROM dbo.Business WHERE IsRepayment=0";

            DataTable dt = MSSQLDBServer.ExecuteDatatable(ConnectString, QueryText, null);

            return dt;
        }

        public static DataTable GetDataTable1()
        {
            string ConnectString=ConfigurationManager.ConnectionStrings["VcreditDW"].ToString();
            string QueryText = string.Format(@"
                SELECT  ContractNo AS '合同号' ,
                        CustomerName AS '客户姓名' ,
                        CustomerProperty AS '客户性质' ,
                        ReceiptVersion AS '借款借据版本' ,
                        LoanCapital AS '贷款金额' ,
                        ResidualCapital AS '剩余本金' ,
                        OverdueAmount AS '逾期金额' ,
                        Capital AS '本金' ,
                        Interest AS '利息' ,
                        ManagementFee AS '管理费' ,
                        CapitalInterestLoss AS '本息扣失' ,
                        ServiceFee AS '服务费' ,
                        GuaranteeFee AS '担保管理费' ,
                        ServiceFeeLoss AS '服务费扣失' ,
                        Penalty AS '罚息' ,
                        BillFee AS '账单费用' ,
                        LoanPeriod AS '贷款期数' ,
                        RepayPeriod AS '贷款期限内已还期数（全部还款）' ,
                        CASE WHEN OverdueAmount > 0 THEN '是'
                                ELSE '否'
                        END AS '是否欠费' ,
                        OverdueDate AS '逾期日期' ,
                        OverdueDays AS '逾期天数' ,
                        TodayOverdueMark '当日逾期标记' ,
                        Guarantee AS '担保' ,
                        GuaranteeDate AS '担保日期' ,
                        GuaranteeAmount AS '担保金额' ,
                        OutStatus AS '委外状态' ,
                        LitigationStatus AS '诉讼状态' ,
                        CLoanStatus AS '提前清贷状态' ,
                        SpecialPolicy AS '特殊政策' ,
                        Product AS '贷款产品种类' ,
                        ProductType AS '产品类型' ,
                        LendingSide AS '信托方（放款方）' ,
                        ServiceSide AS '服务方' ,
                        LoanTime AS '放款日期' ,
                        SalesChannels AS '销售渠道' ,
                        SigningCity AS '城市' ,
                        SigningShop AS '签约门店' ,
                        SalesTeam AS '团队' ,
                        SalesStaff AS '经办人' ,
                        BusinessID AS '业务号' ,
                        SecondSales AS '二次加贷' ,
                        CustomerID AS '客户号' ,
                        HouseHold AS '户籍' ,
                        DATEADD(DAY, -1, StatisticsDate) AS '日期' ,
                        DivisionName AS '分部' ,
                        DATEPART(DAY, RelativeDate) AS '账单日' ,
                        Guarantee AS '担保方' ,
                        CustomerChannel AS '客户渠道' ,
                        RiskLevel AS '风险等级' ,
                        SocialMonths AS '社保缴纳月数' ,
                        AccumMonths AS '公积金缴纳月数' ,
                        ZhengXinCount AS '征信查询次数' ,
                        EnterBlackListTime AS '进入黑名单时间' ,
                        AdminStore AS '管理门店' ,
                        ChannelOTwoO AS '订单来源' ,
                        MarketStore AS '营销门店' ,
                        MarketTeam AS '营销团队' ,
                        MarketStaff AS '营销人' ,
                        ROW_NUMBER() OVER ( ORDER BY OverID ) RowID
                FROM    dbo.OverDueStatic WITH ( NOLOCK )
                WHERE   StatisticsDate = '{0}'
	                    AND DivisionName <> '网络金融'
	                    AND DivisionName <> '市场部'
                ORDER BY ContractNo", DateTime.Now.ToString("yyyy-MM-dd"));

            DataTable dt = MSSQLDBServer.ExecuteDatatable(ConnectString, QueryText, null);

            return dt;
        }
    }
}
