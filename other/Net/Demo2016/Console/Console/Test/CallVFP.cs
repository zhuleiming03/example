using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;

namespace ConsoleDemo
{
    public class CallVFP
    {
        public static void Test()
        {
            string url1 = "";
            var data1 = new { bid = "10316771" };
            string param1 = ToJson(data1);
            string result1 = UploadData(url1, param1);

            string url2 = "";
            var data2 = new
            {
                transactionId = "123456",
                threadId = "",
                bid = "10239400",
                reason = "橙分期(上海银行)退货",
                fundCode = "000000000000",
                userId = "0"
            };
            string param2 = ToJson(data2);
            ServicePointManager.Expect100Continue = false;
            string result2 = UploadData(url2, param2);

            string aa = result2;

        }

        public static string ToJson(object value)
        {
            return JsonConvert.SerializeObject(value);
        }

        public static string UploadData(string url, string data)
        {
            Dictionary<string, string> headers = new Dictionary<string, string>();
            headers.Add("Content-Type", "application/json");
            headers.Add("X-CLIENT-ID", "vbs");
            headers.Add("apiToken", "5f6c7e3d-6aca-448d-a3f2-2ee31fe4f943");
            return UploadData(url, data, headers);
        }

        public static string UploadData(string url, string data, Dictionary<string, string> headers)
        {
            byte[] postData = Encoding.UTF8.GetBytes(data);
            WebClient webClient = new WebClient();
            foreach (var header in headers)
            {
                webClient.Headers.Add(header.Key, header.Value);
            }
            byte[] responseData = webClient.UploadData(url, "POST", postData);
            return Encoding.UTF8.GetString(responseData);
        }
    }
}
