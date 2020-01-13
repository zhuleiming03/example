﻿using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;

namespace Server
{
    public interface IDataBaseServer
    {
        /// <summary>
        /// 数据库连接字符串
        /// </summary>
        string ConnectString { get; set; }

        /// <summary>
        /// 返回受影响的行数
        /// </summary>
        /// <param name="commandText"></param>
        /// <param name="commandParameters"></param>
        /// <param name="commandType"></param>
        /// <param name="transaction"></param>
        /// <returns></returns>
        Int32 ExecuteNonQuery(String commandText, IDataParameter[] commandParameters = null,
            CommandType commandType = CommandType.Text, IDbTransaction transaction = null);

        /// <summary>
        /// 带Out或Output的无返回执行
        /// </summary>
        /// <param name="commandText"></param>
        /// <param name="outParameters"></param>
        /// <param name="commandParameters"></param>
        /// <param name="commandType"></param>
        /// <param name="transaction"></param>
        /// <returns></returns>
        Int32 ExecuteNonQuery(String commandText, out Dictionary<String, Object> outParameters, IDataParameter[] commandParameters,
            CommandType commandType = CommandType.StoredProcedure, IDbTransaction transaction = null);

        /// <summary>
        /// 返回第一行第一列的值
        /// </summary>
        /// <param name="commandText"></param>
        /// <param name="commandParameters"></param>
        /// <param name="commandType"></param>
        /// <param name="transaction"></param>
        /// <returns></returns>
        Object ExecuteScalar(String commandText, IDataParameter[] commandParameters = null, CommandType commandType = CommandType.Text,
            IDbTransaction transaction = null);

        /// <summary>
        /// 返回查询表
        /// </summary>
        /// <param name="commandText"></param>
        /// <param name="commandParameters"></param>
        /// <param name="commandType"></param>
        /// <param name="transaction"></param>
        /// <returns></returns>
        DataTable ExecuteDatatable(String commandText, IDataParameter[] commandParameters = null, CommandType commandType = CommandType.Text,
            IDbTransaction transaction = null);

        /// <summary>
        /// 带Out或Output的查询表
        /// </summary>
        /// <param name="commandText"></param>
        /// <param name="outParameters"></param>
        /// <param name="commandParameters"></param>
        /// <param name="commandType"></param>
        /// <param name="transaction"></param>
        /// <returns></returns>
        DataTable ExecuteDatatable(String commandText, out Dictionary<String, Object> outParameters, IDataParameter[] commandParameters,
            CommandType commandType = CommandType.StoredProcedure, IDbTransaction transaction = null);

        /// <summary>
        /// 返回查询集合
        /// </summary>
        /// <param name="commandText"></param>
        /// <param name="commandParameters"></param>
        /// <param name="commandType"></param>
        /// <param name="transaction"></param>
        /// <returns></returns>
        DataSet ExecuteDataset(String commandText, IDataParameter[] commandParameters = null, CommandType commandType = CommandType.Text,
            IDbTransaction transaction = null);

        /// <summary>
        /// 带事务的批量导入
        /// </summary>
        /// <param name="dataTable"></param>
        /// <param name="transaction"></param>
        /// <param name="batchSize"></param>
        /// <param name="bulkCopyTimeout"></param>
        /// <param name="copyOptions"></param>
        /// <returns></returns>
        Boolean ExecuteBulkCopy(DataTable dataTable, SqlTransaction transaction, Int32 batchSize = 10000, Int32 bulkCopyTimeout = 120,
            SqlBulkCopyOptions copyOptions = SqlBulkCopyOptions.Default);

        /// <summary>
        /// 批量导入
        /// </summary>
        /// <param name="dataTable"></param>
        /// <param name="batchSize"></param>
        /// <param name="bulkCopyTimeout"></param>
        /// <param name="copyOptions"></param>
        /// <returns></returns>
        Boolean ExecuteBulkCopy(DataTable dataTable, Int32 batchSize = 10000, Int32 bulkCopyTimeout = 60,
            SqlBulkCopyOptions copyOptions = SqlBulkCopyOptions.Default);
    }
}
