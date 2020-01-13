using System;
using System.Collections.Generic;
using System.Data;
using System.Reflection;

namespace Server
{
    public static class ToDataTableServer
    {
        /// <summary>
        /// 根据泛型Entity(T)生成该实体类对应的内存中的DataTable的表结构
        /// </summary>
        /// <typeparam name="T">实体类型</typeparam>
        /// <param name="t">实体实例</param>
        /// <returns>只有列名的空表</returns>
        private static DataTable GetDataTableSchema<T>(this T t)
        {
            try
            {
                DataTable Table = new DataTable();
                //获取实体类型
                Type EntityType = typeof(T);
                //表名为类名
                Table.TableName = EntityType.Name;

                foreach (PropertyInfo Property in EntityType.GetProperties())
                {
                    //如果特性的类型是泛型类型如：System.Nullable<DateTime>
                    if (Property.PropertyType.IsGenericType)
                        Table.Columns.Add(new DataColumn(Property.Name, Property.PropertyType.GetGenericArguments()[0]));
                    else
                        Table.Columns.Add(new DataColumn(Property.Name, Property.PropertyType));
                }

                return Table;
            }
            catch
            {
                throw;
            }
        }

        /// <summary>
        /// 将泛型List<T>转换成该泛型的DataTable
        /// </summary>
        /// <typeparam name="T">实体类</typeparam>
        /// <param name="list">List数据源</param>
        /// <returns>DataTable数据集</returns>
        public static DataTable ToDataTable<T>(this List<T> list)
        {
            try
            {
                //实例化一个实体
                T Entity = Activator.CreateInstance<T>();
                //获取实体的类型
                Type EntityType = typeof(T);
                //获取实体的所有特性
                PropertyInfo[] EntityProperties = EntityType.GetProperties();
                //根据Entity构建DataTable表结构
                DataTable Table = Entity.GetDataTableSchema<T>();
                //遍历List
                foreach (T entity in list)
                {
                    //创建数据行
                    DataRow Row = Table.NewRow();
                    //根据特性名称给相同的列名赋值
                    foreach (PropertyInfo Property in EntityProperties)
                    {
                        Row[Property.Name] = Property.GetValue(entity, null) ?? DBNull.Value;
                    }
                    Table.Rows.Add(Row);
                }
                Table.AcceptChanges();
                return Table;
            }
            catch
            {
                throw;
            }
        }
    }
}
