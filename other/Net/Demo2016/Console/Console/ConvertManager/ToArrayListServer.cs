using System;
using System.Collections;
using System.Collections.Generic;
using System.Data;

namespace Server
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
                    Dictionary<String, Object> Dictionary = new Dictionary<String, Object>();
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
