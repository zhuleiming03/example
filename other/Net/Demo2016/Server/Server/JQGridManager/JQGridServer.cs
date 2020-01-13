using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Web.Script.Serialization;

namespace Server
{
    public sealed class JQGridServer
    {
        #region Field

        private IDataBaseServer _dataBaseServer;

        #endregion

        #region Constructor

        public JQGridServer(IDataBaseServer dataBaseServer)
        {
            this._dataBaseServer = dataBaseServer;
        }

        #endregion

        #region Method

        /// <summary>
        /// 获取分页的数据表
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public DataTable GetJQGridDataTable(JQGridEntity entity)
        {
            try
            {
                SqlParameter[] Parameter ={
                new SqlParameter("@OrderBy",SqlDbType.NVarChar,50),
                new SqlParameter("@OrderType",SqlDbType.NVarChar,10),
                new SqlParameter("@Tabel",SqlDbType.NVarChar,50),
                new SqlParameter("@ColumnName",SqlDbType.NVarChar,100),
                new SqlParameter("@Condition",SqlDbType.NVarChar,500),
                new SqlParameter("@Page",SqlDbType.Int,10),
                new SqlParameter("@Row",SqlDbType.Int,10),
                new SqlParameter("@Count",SqlDbType.Int,10)};
                Parameter[0].Value = entity.Sidx;
                Parameter[1].Value = entity.Sord;
                Parameter[2].Value = entity.TableName;
                Parameter[3].Value = entity.ColumnName;
                Parameter[4].Value = entity.Condition;
                Parameter[5].Value = entity.Page;
                Parameter[6].Value = entity.Rows;
                Parameter[7].Direction = ParameterDirection.Output;
                Dictionary<String, Object> OutParameters;

                DataTable Table = _dataBaseServer.ExecuteDatatable("dbo.SP_PageQuery", out OutParameters, Parameter);
                String RowCount = OutParameters["@Count"].ToString();
                Table.ExtendedProperties.Add("ROWCOUNT", RowCount);
                return Table;
            }
            catch
            {
                throw;
            }
        }

        /// <summary>
        /// 获取分页的JSON数据
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public string GetJQGridJson(JQGridEntity entity)
        {
            try
            {
                //获取数据集和总行数
                DataTable Table = GetJQGridDataTable(entity);
                Decimal RowCount = Convert.ToInt32(Table.ExtendedProperties["ROWCOUNT"]);
                //构造集合对象
                var JQGridContent = new
                {
                    total = Math.Ceiling(RowCount / entity.Rows),       //总页数
                    page = entity.Page,                                 //当前页
                    records = RowCount,                                 //查询出的记录数
                    rows = Table.ToArrayList()                          //包含实际数据的数组
                };
                //将集合对象序列化
                String Result = new JavaScriptSerializer().Serialize(JQGridContent);
                return Result;
            }
            catch
            {
                throw;
            }
        }

        #endregion
    }
}
