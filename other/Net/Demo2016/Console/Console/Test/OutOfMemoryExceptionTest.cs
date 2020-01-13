using log4net;
using Server;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Reflection;
using System.Reflection.Emit;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleDemo
{
    public class OutOfMemoryExceptionTest
    {
        ILog logger = LogManager.GetLogger(MethodBase.GetCurrentMethod().DeclaringType);

        public void Test()
        {
            string ConnectString = @"Data Source=.;Initial Catalog=PostLoan;Persist Security Info=True;User ID=;Password=";
            string sqlString = "SELECT * FROM dbo.Business";

            DataTable dt = MSSQLDBServer.ExecuteDatatable(ConnectString, sqlString, null);

            List<Business> businessList = new List<Business>();
            try
            {
                for (int i = 0; i < 10000000; i++)
                {
                    businessList = dt.ConvertToList<Business>();
                    logger.InfoFormat("循环第{0}次，数组有{1}条数据", i, businessList.Count);
                }
            }
            catch(Exception e)
            {
                logger.Error(e);
            }
           
        }
    }


    public static class TypeExtenstion
    {
        /// <summary>
        /// table转成其他类型object
        /// </summary>
        /// <typeparam name="T">类型</typeparam>
        /// <param name="table">datatable</param>
        /// <returns>list</returns>
        public static List<T> ConvertToList<T>(this DataTable table) where T : new()
        {
            if (table == null)
                return null;

            List<T> list = new List<T>();
            if (table.Rows.Count == 0)
                return list;

            DataTableEntityBuilder<T> dte;
            foreach (DataRow dtr in table.Rows)
            {
                dte = DataTableEntityBuilder<T>.CreateBuilder(dtr);
                T t = dte.Build(dtr);
                list.Add(t);
            }
            return list;
        }

        /// <summary>
        /// 扩展方法 将枚举值转换成T枚举元素
        /// </summary>
        public static T ValueToEnum<T>(this object kind)
        {
            return (T)Enum.Parse(typeof(T), kind.ToString());
        }

        /// <summary>
        /// 将字符串转为int，如无法转换则返回默认值
        /// </summary>
        /// <param name="s"></param>
        /// <param name="defaultValue">无法转换时的默认值</param>
        /// <returns>如果可以被成功转换则返回int，否则返回null</returns>
        public static int ToInt(this string s, int defaultValue = 0)
        {
            if (string.IsNullOrWhiteSpace(s)) return defaultValue;

            int v;
            if (!int.TryParse(s, out v)) return defaultValue;

            return v;
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="s"></param>
        /// <param name="defaultValue"></param>
        /// <returns></returns>
        public static long ToLong(this string s, long defaultValue = 0)
        {
            if (string.IsNullOrWhiteSpace(s)) return defaultValue;

            long v;
            if (!long.TryParse(s, out v)) return defaultValue;

            return v;
        }

    }

    public class DataTableEntityBuilder<T>
    {
        private static readonly MethodInfo getValueMethod = typeof(DataRow).GetMethod("get_Item", new Type[] { typeof(int) });
        private static readonly MethodInfo isDBNullMethod = typeof(DataRow).GetMethod("IsNull", new Type[] { typeof(int) });
        private delegate T Load(DataRow dr);

        private Load handler;
        private DataTableEntityBuilder() { }

        public T Build(DataRow dr)
        {
            return handler(dr);
        }

        public static DataTableEntityBuilder<T> CreateBuilder(DataRow dr)
        {
            DataTableEntityBuilder<T> dynamicBuilder = new DataTableEntityBuilder<T>();
            DynamicMethod method = new DynamicMethod("DynamicCreateEntity", typeof(T), new Type[] { typeof(DataRow) }, typeof(T), true);
            ILGenerator generator = method.GetILGenerator();
            LocalBuilder result = generator.DeclareLocal(typeof(T));
            generator.Emit(OpCodes.Newobj, typeof(T).GetConstructor(Type.EmptyTypes));
            generator.Emit(OpCodes.Stloc, result);

            for (int i = 0; i < dr.ItemArray.Length; i++)
            {
                PropertyInfo pi = typeof(T).GetProperty(dr.Table.Columns[i].ColumnName, BindingFlags.IgnoreCase | BindingFlags.Public | BindingFlags.Instance);
                Label endIfLabel = generator.DefineLabel();
                if (pi != null && pi.GetSetMethod() != null)
                {
                    generator.Emit(OpCodes.Ldarg_0);
                    generator.Emit(OpCodes.Ldc_I4, i);
                    generator.Emit(OpCodes.Callvirt, isDBNullMethod);
                    generator.Emit(OpCodes.Brtrue, endIfLabel);
                    generator.Emit(OpCodes.Ldloc, result);
                    generator.Emit(OpCodes.Ldarg_0);
                    generator.Emit(OpCodes.Ldc_I4, i);
                    generator.Emit(OpCodes.Callvirt, getValueMethod);
                    generator.Emit(OpCodes.Unbox_Any, pi.PropertyType);
                    generator.Emit(OpCodes.Callvirt, pi.GetSetMethod());
                    generator.MarkLabel(endIfLabel);
                }
            }
            generator.Emit(OpCodes.Ldloc, result);
            generator.Emit(OpCodes.Ret);
            dynamicBuilder.handler = (Load)method.CreateDelegate(typeof(Load));
            return dynamicBuilder;
        }
    }
}
