using System;
using System.Collections;
using System.Data;
using System.Web.Script.Serialization;

namespace Server
{
    public static class ToJsonServer
    {
        /// <summary>
        /// 将DataTable对象序列化
        /// </summary>
        /// <param name="dt"></param>
        /// <returns></returns>
        public static String ToJson(this DataTable Table)
        {
            try
            {
                JavaScriptSerializer JavaScriptSerializer = new JavaScriptSerializer();
                //取得最大数值
                JavaScriptSerializer.MaxJsonLength = Int32.MaxValue;
                //1、将DataTable转成ArrayList对象
                ArrayList List = Table.ToArrayList();
                //2、将ArrayList数据转成json数据
                return JavaScriptSerializer.Serialize(List);
            }
            catch
            {
                throw;
            }
        }
    }
}
