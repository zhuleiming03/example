using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;

namespace Common
{
    public sealed class MSSQLDataBaseServer 
    {
        public MSSQLDataBaseServer(string dbString)
        {
            Connectstring = ConfigurationManager.ConnectionStrings[dbString].ToString();
        }

        #region Property

        public string Connectstring { get; set; }

        #endregion

        #region Method

        /// <summary>
        /// 返回受影响的行数
        /// </summary>
        /// <param name="commandText"></param>
        /// <param name="commandParameters"></param>
        /// <param name="commandType"></param>
        /// <param name="transaction"></param>
        /// <returns></returns>
        public int ExecuteNonQuery(string commandText, IDataParameter[] commandParameters = null, CommandType commandType = CommandType.Text,
            IDbTransaction transaction = null)
        {
            return MSSQLDBServer.ExecuteNonQuery(Connectstring, commandText, commandParameters, commandType, transaction);
        }

        /// <summary>
        /// 带Out或Output的无返回执行
        /// </summary>
        /// <param name="commandText"></param>
        /// <param name="outParameters"></param>
        /// <param name="commandParameters"></param>
        /// <param name="commandType"></param>
        /// <param name="transaction"></param>
        /// <returns></returns>
        public int ExecuteNonQuery(string commandText, out Dictionary<string, object> outParameters, IDataParameter[] commandParameters,
            CommandType commandType = CommandType.StoredProcedure, IDbTransaction transaction = null)
        {
            return MSSQLDBServer.ExecuteNonQuery(Connectstring, commandText, out outParameters, commandParameters, commandType, transaction);
        }

        /// <summary>
        /// 返回第一行第一列的值
        /// </summary>
        /// <param name="commandText"></param>
        /// <param name="commandParameters"></param>
        /// <param name="commandType"></param>
        /// <param name="transaction"></param>
        /// <returns></returns>
        public object ExecuteScalar(string commandText, IDataParameter[] commandParameters = null, CommandType commandType = CommandType.Text,
            IDbTransaction transaction = null)
        {
            return MSSQLDBServer.ExecuteScalar(Connectstring, commandText, commandParameters, commandType, transaction);
        }

        /// <summary>
        /// 返回查询表
        /// </summary>
        /// <param name="commandText"></param>
        /// <param name="commandParameters"></param>
        /// <param name="commandType"></param>
        /// <param name="transaction"></param>
        /// <returns></returns>
        public DataTable ExecuteDatatable(string commandText, IDataParameter[] commandParameters = null, CommandType commandType = CommandType.Text,
            IDbTransaction transaction = null)
        {
            return MSSQLDBServer.ExecuteDatatable(Connectstring, commandText, commandParameters, commandType, transaction);
        }

        /// <summary>
        /// 带Out或Output的查询表
        /// </summary>
        /// <param name="commandText"></param>
        /// <param name="outParameters"></param>
        /// <param name="commandParameters"></param>
        /// <param name="commandType"></param>
        /// <param name="transaction"></param>
        /// <returns></returns>
        public DataTable ExecuteDatatable(string commandText, out Dictionary<string, object> outParameters, IDataParameter[] commandParameters,
            CommandType commandType = CommandType.StoredProcedure, IDbTransaction transaction = null)
        {
            return MSSQLDBServer.ExecuteDatatable(Connectstring, commandText, out outParameters, commandParameters, commandType, transaction);
        }

        /// <summary>
        /// 返回查询集合
        /// </summary>
        /// <param name="commandText"></param>
        /// <param name="commandParameters"></param>
        /// <param name="commandType"></param>
        /// <param name="transaction"></param>
        /// <returns></returns>
        public DataSet ExecuteDataset(string commandText, IDataParameter[] commandParameters = null, CommandType commandType = CommandType.Text,
            IDbTransaction transaction = null)
        {
            return MSSQLDBServer.ExecuteDataset(Connectstring, commandText, commandParameters, commandType, transaction);
        }

        /// <summary>
        /// 带事务的批量导入
        /// </summary>
        /// <param name="dataTable"></param>
        /// <param name="transaction"></param>
        /// <param name="batchSize"></param>
        /// <param name="bulkCopyTimeout"></param>
        /// <param name="copyOptions"></param>
        /// <returns></returns>
        public bool ExecuteBulkCopy(DataTable dataTable, SqlTransaction transaction, int batchSize = 10000,
            int bulkCopyTimeout = 120, SqlBulkCopyOptions copyOptions = SqlBulkCopyOptions.Default)
        {
            return MSSQLDBServer.SQLBulkCopy(Connectstring, dataTable, transaction, batchSize, bulkCopyTimeout, copyOptions);
        }

        /// <summary>
        /// 批量导入
        /// </summary>
        /// <param name="dataTable"></param>
        /// <param name="batchSize"></param>
        /// <param name="bulkCopyTimeout"></param>
        /// <param name="copyOptions"></param>
        /// <returns></returns>
        public bool ExecuteBulkCopy(DataTable dataTable, int batchSize = 10000, int bulkCopyTimeout = 60,
            SqlBulkCopyOptions copyOptions = SqlBulkCopyOptions.Default)
        {
            return MSSQLDBServer.SQLBulkCopy(Connectstring, dataTable, batchSize, bulkCopyTimeout, copyOptions);
        }

        /// <summary>
        /// 分页查询
        /// </summary>
        /// <param name="commandText"></param>
        /// <param name="pageCode">页码（从1开始）</param>
        /// <param name="pageSize">页行数</param>
        /// <param name="orderColumn">排序列</param>
        /// <param name="asc">true:升序；false:降序</param>
        /// <returns></returns>
        public DataTable ExecutePageDatatable(string commandText, int pageCode, int pageSize, string orderColumn, bool asc = true)
        {
            string SQLString = string.Format(@"
                SELECT  COUNT(1) FROM ( {0} ) ObjectData;

                SELECT  *
                FROM    ( SELECT    ROW_NUMBER() OVER ( ORDER BY {1} {2} ) AS ID , *
                          FROM      ( {0} ) ObjectData
                        ) PageTemp
                WHERE   PageTemp.ID BETWEEN {3} AND {4};",
                commandText, orderColumn, asc ? " ASC " : " DESC ", (pageCode - 1) * pageSize + 1, pageCode * pageSize);

            try
            {
                DataSet ResultSet = MSSQLDBServer.ExecuteDataset(Connectstring, SQLString);

                int Total = int.Parse(ResultSet.Tables[0].Rows[0][0].ToString());

                DataTable ResultTable = ResultSet.Tables[1];
                ResultTable.ExtendedProperties.Add("Total", Total);

                return ResultTable;
            }
            catch
            {
                string Error = string.Format("SQL:{0}", SQLString.Replace('\n', ' '));
                throw new Exception(Error);
            }
        }

        #endregion
    }
}
