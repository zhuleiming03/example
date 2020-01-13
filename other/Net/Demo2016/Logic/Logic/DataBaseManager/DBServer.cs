using Server;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;

namespace Logic
{
    internal sealed class DBServer
    {
        #region Field

        private IDataBaseServer _dataBaseServer;

        private const String _connectStringName = "CONN_STRING";

        #endregion

        #region Property

        internal IDataBaseServer DataBaseServer
        {
            get
            {
                return _dataBaseServer;
            }
            set
            {
                _dataBaseServer = value;
            }
        }

        #endregion

        #region Constructor

        internal DBServer()
        {
            this._dataBaseServer = new MSSQLDataBaseServer();           
            this._dataBaseServer.ConnectString = ConfigurationManager.ConnectionStrings[_connectStringName].ConnectionString;
            DataBaseServer = _dataBaseServer;
        }

        #endregion

        #region Method

        /// <summary>
        /// 返回受影响的行数
        /// </summary>
        /// <param name="commandText"></param>
        /// <param name="commandParameters"></param>
        /// <param name="commandType"></param>
        /// <returns></returns>
        internal Int32 ExecuteNonQuery(String commandText, SqlParameter[] commandParameters, CommandType commandType = CommandType.Text)
        {
            return _dataBaseServer.ExecuteNonQuery(commandText, commandParameters, commandType);
        }

        /// <summary>
        /// 带Out或Output的无返回执行,常用于存储过程
        /// </summary>
        /// <param name="commandText"></param>
        /// <param name="outParameters"></param>
        /// <param name="commandParameters"></param>
        /// <param name="commandType"></param>
        /// <returns></returns>
        internal Int32 ExecuteNonQueryWithOutput(String commandText, out Dictionary<String, Object> outParameters,
            SqlParameter[] commandParameters, CommandType commandType)
        {
            return _dataBaseServer.ExecuteNonQuery(commandText, out outParameters, commandParameters, commandType);
        }

        /// <summary>
        /// 返回第一行第一列的值
        /// </summary>
        /// <param name="commandText"></param>
        /// <param name="commandParameters"></param>
        /// <param name="commandType"></param>
        /// <returns></returns>
        internal Object ExecuteScalar(String commandText, SqlParameter[] commandParameters = null, CommandType commandType = CommandType.Text)
        {
            return _dataBaseServer.ExecuteScalar(commandText, commandParameters, commandType);
        }

        /// <summary>
        /// 返回查询表
        /// </summary>
        /// <param name="commandText"></param>
        /// <param name="commandParameters"></param>
        /// <param name="commandType"></param>
        /// <returns></returns>
        internal DataTable ExecuteDatatable(String commandText, SqlParameter[] commandParameters = null, CommandType commandType = CommandType.Text)
        {
            return _dataBaseServer.ExecuteDatatable(commandText, commandParameters, commandType);
        }

        /// <summary>
        /// 带Out或Output的查询表,常用于存储过程
        /// </summary>
        /// <param name="commandText"></param>
        /// <param name="outParameters"></param>
        /// <param name="commandParameters"></param>
        /// <param name="commandType"></param>
        /// <returns></returns>
        internal DataTable ExecuteDatatableWithOutput(String commandText, out Dictionary<String, Object> outParameters, 
            SqlParameter[] commandParameters, CommandType commandType)
        {
            return _dataBaseServer.ExecuteDatatable(commandText, out outParameters, commandParameters, commandType);
        }

        /// <summary>
        /// 返回查询集合
        /// </summary>
        /// <param name="commandText"></param>
        /// <param name="commandParameters"></param>
        /// <param name="commandType"></param>
        /// <returns></returns>
        internal DataSet ExecuteDataset(String commandText, SqlParameter[] commandParameters = null, CommandType commandType = CommandType.Text)
        {
            return _dataBaseServer.ExecuteDataset(commandText, commandParameters, commandType);
        }

        /// <summary>
        /// 批量导入
        /// </summary>
        /// <param name="dataTable"></param>
        /// <returns></returns>
        internal bool SQLBulkCopy(DataTable dataTable)
        {
            return _dataBaseServer.SQLBulkCopy(dataTable);
        }

        #endregion
    }
}
