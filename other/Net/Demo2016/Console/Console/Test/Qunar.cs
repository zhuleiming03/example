using Aspose.Cells;
using log4net;
using Newtonsoft.Json;
using Server;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Web.Script.Serialization;
using System.Data;
using System.Linq;
using System.Net;
using System.Reflection;
using System.Threading;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleDemo
{
    public class Qunar
    {

        public static void ReplyInstruction()
        {

            ReplyInstructionInputParmModel model = new ReplyInstructionInputParmModel();

            //SELECT FillStatus, BusinessID, REPLACE(REPLACE(REPLACE(CONVERT(varchar(100), RepayTime, 120), '-', ''), ':', ''), ' ', ''),RepayAmt,RepayType,BillIndex,JQHDeductId,ReCapital,ReInterest,RePunitiveInterest FROM dbo.JQHDeductRecord
            //WHERE BusinessID = 5582820

            model.BusinessId = 6161589;
            model.RepayTime = "20171206152017";
            model.RepayAmt = 681.46;
            model.RepayStatus = 1;
            model.RepayPeriod = 3;
            model.RepayKey = "REPAY20171206152017917034041124";
            model.ReCapital = 668.00;
            model.ReInterest = 13.46;
            model.RePunitiveInterest = 0.00;
            model.IsRepair = true;

            JavaScriptSerializer JavaScriptSerializer = new JavaScriptSerializer();

            string url = "";

            string RequestStr = JsonConvert.SerializeObject(model);

            string result = SendRequest(RequestStr, url);

            ResultBaseModel ResponseInfo = JsonConvert.DeserializeObject<ResultBaseModel>(result);

            ILog logger = LogManager.GetLogger(MethodBase.GetCurrentMethod().DeclaringType);

            string resultmsg = string.Format("订单：{0}，结果：{1}，信息：{2}",
                model.BusinessId, ResponseInfo.IsSuccess, ResponseInfo.ErrorInfo);

            logger.Info(resultmsg);

            Thread.Sleep(2000);
        }

        public static void SyncTest()
        {
            Console.WriteLine("开始同步");

            string url = "";


            #region

            List<int> BIDList = new List<int>
                      {5370689 };

            #endregion

            for (int i = 0; i < BIDList.Count; i++)
            {

                //AlterRecevied(BIDList[i]);

                string RequestStr = JsonConvert.SerializeObject(BIDList[i]);

                string result = SendRequest(RequestStr, url);

                ResultBaseModel ResponseInfo = JsonConvert.DeserializeObject<ResultBaseModel>(result);



                ILog logger = LogManager.GetLogger(MethodBase.GetCurrentMethod().DeclaringType);

                string resultmsg = string.Format("订单：{0}，结果：{1}，信息：{2}", BIDList[i], ResponseInfo.IsSuccess, ResponseInfo.ErrorInfo);

                logger.Info(resultmsg);
            }


            Console.WriteLine("同步结束");
        }

        public static void Query()
        {
            try
            {

                Workbook book = new Workbook();

                Worksheet cellSheet = book.Worksheets[0];

                Cells cells = cellSheet.Cells;

                Console.WriteLine("开始查询");

                List<int> ListBid = GetBID();

                List<BusinessMessage> QunarList = QueryQunar(ListBid);

                DataTable dt = QunarList.ToDataTable<BusinessMessage>();

                Console.WriteLine("开始生成Excel");

                cells.ImportDataTable(dt, false, 1, 0);

                Console.WriteLine("保存Excel");


                string SaveUrl = ConfigurationManager.AppSettings["FTPSaveFile"].ToString();

                string Url = string.Format(@"{0}{1}问题数据.xlsx", SaveUrl, DateTime.Now.ToString("yyyyMMdd"));

                book.Save(Url);

                Console.WriteLine("保存成功");
            }
            catch (Exception e)
            {
                Console.WriteLine(e.ToString());
                Console.ReadLine();
            }
        }

        private static List<int> GetBID()
        {
            string ConnectString = @"Data Source=;Initial Catalog=PostLoan;Persist Security Info=True;User ID=;Password=";
            string QueryText = @"SELECT BusinessID FROM dbo.Business WHERE LoanKind = 'LOANKIND/JIEQUHUA' AND IsRepayment = 1 AND FrozenNo=''";

            DataTable dt = MSSQLDBServer.ExecuteDatatable(ConnectString, QueryText, null);

            List<int> BidList = new List<int>();
            for (int i = 0; i < dt.Rows.Count; i++)
            {
                int tmp = int.Parse(dt.Rows[i][0].ToString());
                BidList.Add(tmp);
            }
            return BidList;
        }

        private static void AlterRecevied(int bus)
        {
            string ConnectString = @"Data Source=;Initial Catalog=PostLoan;User ID=;Password=";
            string QueryText = string.Format(@"DECLARE @BID INT={0}

                    SELECT BillID INTO #Bill FROM dbo.Bill WHERE BusinessID=@BID

                    INSERT dbo.Received
                            ( BillID ,BillItemID ,Amount ,ReceivedType ,PayID ,ReceivedTime ,CreateTime ,OperatorID ,Explain ,DeductionID ,ToAccountID ,TIMESTAMP)
                    SELECT BillID,BillItemID,-Amount,102,0,ReceivedTime,GETDATE(),0,'去哪儿',0,24,NULL
                    FROM dbo.Received WHERE BillID IN (SELECT BillID FROM #Bill)

                    UPDATE dbo.Bill SET BillStatus=1 WHERE BillID IN (SELECT BillID FROM #Bill)

                    UPDATE dbo.BillItem SET ReceivedAmt=0 WHERE BillID IN (SELECT BillID FROM #Bill)

                    UPDATE dbo.Business SET FrozenNo='' WHERE BusinessID=@BID

                    UPDATE dbo.JQHDeductRecord SET FillStatus=0 WHERE BusinessID=@BID AND FillStatus=4

                    EXEC dbo.pro_Business_UpdateForBusinessID @BID

                    DROP TABLE #Bill", bus);

            MSSQLDBServer.ExecuteNonQuery(ConnectString, QueryText, null);
        }

        private static List<int> GetBID707()
        {
            string ConnectString = @"Data Source=;Initial Catalog=PostLoan;Persist Security Info=True;User ID=;Password=";
            string QueryText = @"SELECT  *
                    FROM    dbo.Business
                    WHERE IsRepayment = 1 AND FrozenNo='' AND BusinessID IN 
                    ( 5848164, 5849787, 5851272, 5851339, 5854796, 5862310,
                        5862979, 5863870, 5864904, 5574854, 5749400, 5751300,
                        5753519, 5761455, 5762181, 5763356, 5764224, 5765592,
                        5765734, 5384360, 5384646, 5384771, 5384889, 5385104,
                        5385150, 5385226, 5385854, 5387206, 5397525, 5400993,
                        5402245, 5404284, 5476402, 5480999, 5484953, 5488909,
                        5499660, 5683421, 5686256, 5690188, 5694568, 5695191,
                        5697188, 5704052, 5307769, 5309757, 5312598, 5313584,
                        5319946, 5323791, 5324574, 5325514, 5326053, 5326916,
                        5168957, 5246173, 5248952, 5250479, 5250799, 5263707,
                        5264573, 5265471, 5265503, 5865697, 5866540, 5869463,
                        5870121, 5872734, 5881020, 5882915, 5885747, 5582599,
                        5590585, 5782948, 5786495, 5787441, 5794167, 5796044,
                        5801411, 5803020, 5405618, 5414147, 5415259, 5416516,
                        5420988, 5426625, 5509261, 5511486, 5518511, 5523324,
                        5524095, 5714369, 5714602, 5715009, 5715410, 5716635,
                        5716663, 5717380, 5717693, 5895634, 5895683, 5268510,
                        5268653, 5272839, 5273599, 5275077, 5275436, 5275749,
                        5277069, 5277730, 5278412, 5278642, 5279262, 5279352,
                        5280048, 5281307, 5287630, 5289321, 5331328, 5338381,
                        5338507, 5340264, 5343037, 5344678, 5345653, 5347527,
                        5355681, 5606844, 5609509, 5812338, 5813548, 5826469,
                        5827153, 5827157, 5434749, 5437024, 5442798, 5447212,
                        5450212, 5455680, 5724530, 5724898, 5724942, 5725851,
                        5726700, 5727265, 5728690, 5730694, 5733966, 5529756,
                        5536218, 5537972, 5540926, 5543061, 5546985, 5912189,
                        5916885, 5920975, 5924739, 5925434, 5291215, 5297284,
                        5299537, 5299805, 5302471, 5303546, 5306374, 5663359,
                        5665811, 5667707, 5671465, 5674214, 5676533, 5356034,
                        5357494, 5359412, 5360270, 5367259, 5367927, 5370005,
                        5370784, 5375176, 5835720, 5836046, 5837236, 5737736,
                        5741119, 5463502, 5552942, 5558482, 6631306, 6639863,
                        6639918, 6651775, 6651788, 6665728, 6665729 ) ";

            DataTable dt = MSSQLDBServer.ExecuteDatatable(ConnectString, QueryText, null);

            List<int> BidList = new List<int>();
            for (int i = 0; i < dt.Rows.Count; i++)
            {
                int tmp = int.Parse(dt.Rows[i][0].ToString());
                BidList.Add(tmp);
            }
            return BidList;
        }

        private static string SendRequest(string requestStr, string url)
        {
            // 发送信息转字节数组
            byte[] postData = Encoding.UTF8.GetBytes(requestStr);
            // 发送第三方接口
            WebClient webClient = new WebClient();
            webClient.Headers.Add("Content-Type", "application/json");
            // 获取第三方返回的请求结果
            byte[] responseData = webClient.UploadData(url, "POST", postData);
            // 将获取到的返回结果字节数组转换成字符串
            return Encoding.UTF8.GetString(responseData);
        }

        private static List<BusinessMessage> QueryQunar(List<int> bidList)
        {
            List<BusinessMessage> AllErrorList = new List<BusinessMessage>();

            //查询最大笔数
            int BatchMaxCount = 100;
            //查询当前笔数
            int BatchCurrentCount = BatchMaxCount;
            //查询次数
            int BatchTime = 0;
            if (bidList.Count % BatchMaxCount == 0)
            {
                BatchTime = bidList.Count / BatchMaxCount;
            }
            else
            {
                BatchTime = bidList.Count / BatchMaxCount + 1;
            }

            string url = "";

            Console.WriteLine(string.Format("合计查询{0}批", BatchTime));

            for (int i = 0; i < BatchTime; i++)
            {

                //当前批次查询笔数
                if (bidList.Count < BatchMaxCount)
                {
                    BatchCurrentCount = bidList.Count;
                }

                //当批次校验的订单号
                List<int> BIDList = bidList.Take(BatchCurrentCount).ToList();

                string RequestStr = JsonConvert.SerializeObject(BIDList);

                //获取当前批次订单的去哪儿还款计划信息
                string result = SendRequest(RequestStr, url);

                ResultModel ResponseInfo = JsonConvert.DeserializeObject<ResultModel>(result);

                AllErrorList.AddRange(ResponseInfo.ResultContent);


                //移除已查询过的订单
                bidList.RemoveRange(0, BatchCurrentCount);

                Console.WriteLine(string.Format("第{0}批已查询完", i + 1));
            }

            return AllErrorList;
        }
    }


    public class ResultBaseModel
    {
        /// <summary>
        /// 是否成功
        /// </summary>
        public bool IsSuccess { get; set; }

        /// <summary>
        /// 错误信息 (失败时,返回调用失败信息)
        /// </summary>
        public string ErrorInfo { get; set; }
    }

    public class ResultModel
    {
        /// <summary>
        /// 是否成功
        /// </summary>
        public bool IsSuccess { get; set; }

        /// <summary>
        /// 错误信息 (失败时,返回调用失败信息)
        /// </summary>
        public string ErrorInfo { get; set; }

        /// <summary>
        /// 响应结果
        /// </summary>
        public List<BusinessMessage> ResultContent { get; set; }
    }

    public class BusinessMessage
    {
        /// <summary>
        /// 订单号
        /// </summary>
        public int BusinessId { get; set; }

        /// <summary>
        /// 订单状态
        /// </summary>
        public string Message { get; set; }
    }

    public class ReplyInstructionInputParmModel
    {
        /// <summary>
        /// 订单号
        /// </summary>
        public int BusinessId { get; set; }

        /// <summary>
        /// 还款时间
        /// </summary>
        public string RepayTime { get; set; }

        /// <summary>
        /// 还款金额
        /// </summary>
        public double RepayAmt { get; set; }

        /// <summary>
        /// 还款类型(1,按期还款;2,提前清贷)
        /// </summary>
        public int RepayStatus { get; set; }

        /// <summary>
        /// 还款期数
        /// </summary>
        public int RepayPeriod { get; set; }

        /// <summary>
        /// 还款流水号
        /// </summary>
        public string RepayKey { get; set; }

        /// <summary>
        /// 已还本金
        /// </summary>
        public double ReCapital { get; set; }

        /// <summary>
        /// 已还贷款利息
        /// </summary>
        public double ReInterest { get; set; }

        /// <summary>
        /// 已还罚息
        /// </summary>
        public double RePunitiveInterest { get; set; }

        public bool IsRepair { get; set; }
    }
}
