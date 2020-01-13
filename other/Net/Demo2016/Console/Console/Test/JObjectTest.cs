using Newtonsoft.Json.Linq;
using System;

namespace ConsoleDemo
{
    public static class JObjectTest
    {
        public static void Test()
        {
            string srcString = "{Status:0}";
            string info = string.Empty;

            JObject temp = JObject.Parse(srcString);
            JToken test = temp["Exception"];
            if (test != null)
                info = test.ToString();

            bool success = false;
            if (temp["Status"] != null && temp["Status"].ToString() == "0")
                success = true;

            Console.Write(string.Format("Status:{0} Exception:{1}", success.ToString(), info));
        }
    }
}
