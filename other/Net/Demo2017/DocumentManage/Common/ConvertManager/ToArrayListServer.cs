using System.Collections;
using System.Collections.Generic;
using System.Data;

namespace Common
{
    internal static class ToArrayListServer
    {
        /// <summary>
        /// 将DataTable转成ArrayList对象
        /// </summary>
        /// <param name="Table"></param>
        /// <returns></returns>
        internal static ArrayList ToArrayList(this DataTable Table)
        {
            try
            {
                ArrayList ArrayList = new ArrayList();
                foreach (DataRow DataRow in Table.Rows)
                {
                    //实例化一个参数集合
                    Dictionary<string, object> Dictionary = new Dictionary<string, object>();
                    foreach (DataColumn DataColumn in Table.Columns)
                    {
                        Dictionary.Add(DataColumn.ColumnName, DataRow[DataColumn.ColumnName]);
                    }
                    //ArrayList集合中添加键值
                    ArrayList.Add(Dictionary);
                }
                return ArrayList;
            }
            catch
            {
                throw;
            }
        }
    }
}
