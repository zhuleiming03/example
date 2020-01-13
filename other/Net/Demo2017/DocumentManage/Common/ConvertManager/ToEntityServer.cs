using System;
using System.Data;
using System.Reflection;

namespace Common
{
    public static class ToEntityServer
    {
        /// <summary>
        /// 将DataRow转成T实体
        /// </summary>
        /// <typeparam name="T">实体类</typeparam>
        /// <param name="row">数据行数据</param>
        /// <returns>实体实例</returns>
        public static T ToEntity<T>(this DataRow row)
        {
            try
            {
                //获取实体的类型
                Type EntityType = typeof(T);
                //获取实体的所有特性
                PropertyInfo[] EntityPropertys = EntityType.GetProperties();
                //创建实体实例
                T Entity = Activator.CreateInstance<T>();
                //为每个与列名一致的特性赋值
                foreach (PropertyInfo Property in EntityPropertys)
                {
                    if (row.Table.Columns.Contains(Property.Name) && row[Property.Name] != DBNull.Value)
                    {
                        Property.SetValue(Entity, row[Property.Name], null);
                    }
                }
                //返回实体实例
                return Entity;
            }
            catch
            {
                throw;
            }
        }

        /// <summary>
        /// 采用非继承的方式把TModel对象的值赋给TEntity实体
        /// </summary>
        /// <typeparam name="TModel">传值的对象类</typeparam>
        /// <typeparam name="TEntity">被赋值的实体类</typeparam>
        /// <param name="model">传值的对象实例</param>
        /// <returns>实体实例</returns>
        public static TEntity ToEntity<TModel, TEntity>(this TModel model)
        {
            try
            {
                //创建实体实例
                TEntity Entity = Activator.CreateInstance<TEntity>();
                //获取实体类型
                Type TypeTEntity = typeof(TEntity);
                //获取实体的所有特性
                PropertyInfo[] PropertiesTEntity = TypeTEntity.GetProperties();

                //获取对象类型
                Type TypeTModel = typeof(TModel);
                //获取对象的所有特性
                PropertyInfo[] PropertiesTModel = TypeTModel.GetProperties();

                foreach (PropertyInfo PropertyTModel in PropertiesTModel)
                {
                    //如果对象有跟实体一致的特性,则给该实体的特性赋值
                    foreach (PropertyInfo PropertyTEntity in PropertiesTEntity)
                    {
                        if (PropertyTModel.Name == PropertyTEntity.Name)
                        {
                            PropertyTEntity.SetValue(Entity, PropertyTModel.GetValue(model, null), null);
                        }
                    }
                }

                //返回实体实例
                return Entity;
            }
            catch
            {
                throw;
            }
        }
    }
}
