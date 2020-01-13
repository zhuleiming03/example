using System.Collections.Generic;
using System.Data;

namespace Common
{
    public static class ToListServer
    {
        /// <summary>
        /// 将DataTable转成List<T>
        /// </summary>
        /// <typeparam name="T">实体类</typeparam>
        /// <param name="Table">表结构数据</param>
        /// <returns>List数据集合</returns>
        public static List<T> ToList<T>(this DataTable Table)
        {
            if (Table == null || Table.Rows.Count == 0) return null;

            try
            {
                List<T> List = new List<T>();
                for (int i = 0; i < Table.Rows.Count; i++)
                {
                    T Entity = Table.Rows[i].ToEntity<T>();
                    List.Add(Entity);
                }
                return List;
            }
            catch
            {
                throw;
            }
        }
    }
}
