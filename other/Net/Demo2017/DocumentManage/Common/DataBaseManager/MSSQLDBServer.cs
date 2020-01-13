using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Common;
using System.Data.SqlClient;

namespace Common
{
    internal static class MSSQLDBServer
    {
        /// <summary>
        /// 为Command添加Parameter值
        /// </summary>
        /// <param name="command"></param>
        /// <param name="commandParameters"></param>
        private static void AttachParameters(SqlCommand command, IDataParameter[] commandParameters)
        {
            if (commandParameters != null)
            {
                foreach (SqlParameter Parameter in commandParameters)
                {
                    if (Parameter != null)
                    {
                        // Check for derived output value with no value assigned
                        if ((Parameter.Direction == ParameterDirection.InputOutput ||
                            Parameter.Direction == ParameterDirection.Input) &&
                            (Parameter.Value == null))
                        {
                            Parameter.Value = DBNull.Value;
                        }
                        command.Parameters.Add(Parameter);
                    }
                }
            }
        }

        /// <summary>
        /// 配置Command
        /// </summary>
        /// <param name="connection"></param>
        /// <param name="commandType"></param>
        /// <param name="commandText"></param>
        /// <param name="commandParameters"></param>
        /// <param name="transaction"></param>
        /// <returns></returns>
        private static SqlCommand PrepareCommand(DbConnection connection, CommandType commandType, string commandText, IDataParameter[] commandParameters, IDbTransaction transaction)
        {
            SqlCommand Command = new SqlCommand();

            // If the provided connection is not open, we will open it
            if (connection.State != ConnectionState.Open)
            {
                connection.Open();
            }

            // Associate the connection with the command
            Command.Connection = (SqlConnection)connection;

            // Set the command text (stored procedure name or SQL statement)
            Command.CommandText = commandText;

            // If we were provided a transaction, assign it
            if (transaction != null)
            {
                Command.Transaction = (SqlTransaction)transaction;
            }

            // Set the command type
            Command.CommandType = commandType;

            // Attach the command parameters if they are provided
            if (commandParameters != null)
            {
                AttachParameters(Command, commandParameters);
            }
            return Command;
        }

        /// <summary>
        /// 返回受影响的行数
        /// </summary>
        /// <param name="connectionstring"></param>
        /// <param name="commandText"></param>
        /// <param name="commandParameters"></param>
        /// <param name="commandType"></param>
        /// <param name="transaction"></param>
        /// <returns></returns>
        internal static int ExecuteNonQuery(string connectionstring, string commandText, IDataParameter[] commandParameters = null, CommandType commandType = CommandType.Text, IDbTransaction transaction = null)
        {
            try
            {
                using (SqlConnection Connection = new SqlConnection(connectionstring))
                {
                    SqlCommand Command = PrepareCommand(Connection, commandType, commandText, commandParameters, transaction);

                    int ReturnValue = Command.ExecuteNonQuery();

                    // Detach the SqlParameters from the command object, so they can be used again
                    Command.Parameters.Clear();

                    return ReturnValue;
                }
            }
            catch 
            {
                throw;
            }
        }

        /// <summary>
        /// 带Out或Output的无返回执行
        /// </summary>
        /// <param name="connectionstring"></param>
        /// <param name="commandText"></param>
        /// <param name="outParameters"></param>
        /// <param name="commandParameters"></param>
        /// <param name="commandType"></param>
        /// <param name="transaction"></param>
        /// <returns></returns>
        internal static int ExecuteNonQuery(string connectionstring, string commandText, out Dictionary<string, object> outParameters, IDataParameter[] commandParameters, CommandType commandType = CommandType.StoredProcedure, IDbTransaction transaction = null)
        {
            outParameters = new Dictionary<string, object>();

            try
            {
                using (SqlConnection Connection = new SqlConnection(connectionstring))
                {
                    SqlCommand Command = PrepareCommand(Connection, commandType, commandText, commandParameters, transaction);

                    int ReturnValue = Command.ExecuteNonQuery();

                    foreach (SqlParameter Parameter in commandParameters)
                    {
                        if (Parameter != null)
                        {
                            if ((Parameter.Direction == ParameterDirection.InputOutput || Parameter.Direction == ParameterDirection.Output))
                            {
                                outParameters.Add(Parameter.ParameterName, Parameter.Value);
                            }
                        }
                    }

                    // Detach the SqlParameters from the command object, so they can be used again
                    Command.Parameters.Clear();

                    return ReturnValue;
                }
            }
            catch 
            {
                throw;
            }
        }

        /// <summary>
        /// 返回第一行第一列的值
        /// </summary>
        /// <param name="connectionstring"></param>
        /// <param name="commandText"></param>
        /// <param name="commandParameters"></param>
        /// <param name="commandType"></param>
        /// <param name="transaction"></param>
        /// <returns></returns>
        internal static object ExecuteScalar(string connectionstring, string commandText, IDataParameter[] commandParameters = null, CommandType commandType = CommandType.Text, IDbTransaction transaction = null)
        {
            try
            {
                using (SqlConnection Connection = new SqlConnection(connectionstring))
                {
                    SqlCommand Command = PrepareCommand(Connection, commandType, commandText, commandParameters, transaction);

                    // Execute the command & return the results
                    object ReturnValue = Command.ExecuteScalar();

                    // Detach the SqlParameters from the command object, so they can be used again
                    Command.Parameters.Clear();

                    return ReturnValue;
                }
            }
            catch
            {
                throw;
            }
        }

        /// <summary>
        /// 返回查询表
        /// </summary>
        /// <param name="connectionstring"></param>
        /// <param name="commandText"></param>
        /// <param name="commandParameters"></param>
        /// <param name="commandType"></param>
        /// <param name="transaction"></param>
        /// <returns></returns>
        internal static DataTable ExecuteDatatable(string connectionstring, string commandText, IDataParameter[] commandParameters = null, CommandType commandType = CommandType.Text, IDbTransaction transaction = null)
        {
            try
            {
                using (SqlConnection Connection = new SqlConnection(connectionstring))
                {
                    SqlCommand Command = PrepareCommand(Connection, commandType, commandText, commandParameters, transaction);

                    // Create the DataAdapter & DataTable
                    using (SqlDataAdapter DataAdapter = new SqlDataAdapter(Command))
                    {
                        DataTable Tabel = new DataTable();

                        // Fill the DataTable using default values for DataTable names, etc
                        DataAdapter.Fill(Tabel);

                        // Detach the SqlParameters from the command object, so they can be used again
                        Command.Parameters.Clear();

                        // Return the datatable
                        return Tabel;
                    }
                }
            }
            catch 
            {
                throw;
            }
        }

        /// <summary>
        /// 带Out或Output的查询表
        /// </summary>
        /// <param name="connectionstring"></param>
        /// <param name="commandText"></param>
        /// <param name="outParameters"></param>
        /// <param name="commandParameters"></param>
        /// <param name="commandType"></param>
        /// <param name="transaction"></param>
        /// <returns></returns>
        internal static DataTable ExecuteDatatable(string connectionstring, string commandText, out Dictionary<string, object> outParameters, IDataParameter[] commandParameters, CommandType commandType = CommandType.StoredProcedure, IDbTransaction transaction = null)
        {
            outParameters = new Dictionary<string, object>();

            try
            {
                using (SqlConnection Connection = new SqlConnection(connectionstring))
                {
                    SqlCommand Command = PrepareCommand(Connection, commandType, commandText, commandParameters, transaction);

                    // Create the DataAdapter & DataTable
                    using (SqlDataAdapter DataAdapter = new SqlDataAdapter(Command))
                    {
                        DataTable Tabel = new DataTable();

                        // Fill the DataTable using default values for DataTable names, etc
                        DataAdapter.Fill(Tabel);


                        foreach (SqlParameter Parameter in commandParameters)
                        {
                            if (Parameter != null)
                            {
                                if ((Parameter.Direction == ParameterDirection.InputOutput || Parameter.Direction == ParameterDirection.Output))
                                {
                                    outParameters.Add(Parameter.ParameterName, Parameter.Value);
                                }
                            }
                        }

                        // Detach the SqlParameters from the command object, so they can be used again
                        Command.Parameters.Clear();

                        // Return the datatable
                        return Tabel;
                    }
                }
            }
            catch 
            {
                throw;
            }
        }

        /// <summary>
        /// 返回查询集合
        /// </summary>
        /// <param name="connectionstring"></param>
        /// <param name="commandText"></param>
        /// <param name="commandParameters"></param>
        /// <param name="commandType"></param>
        /// <param name="transaction"></param>
        /// <returns></returns>
        internal static DataSet ExecuteDataset(string connectionstring, string commandText, IDataParameter[] commandParameters = null, CommandType commandType = CommandType.Text, IDbTransaction transaction = null)
        {
            try
            {
                using (SqlConnection Connection = new SqlConnection(connectionstring))
                {
                    SqlCommand Command = PrepareCommand(Connection, commandType, commandText, commandParameters, transaction);

                    // Create the DataAdapter & DataSet
                    using (SqlDataAdapter DataAdapter = new SqlDataAdapter(Command))
                    {
                        DataSet Set = new DataSet();

                        DataAdapter.Fill(Set);

                        // Detach the SqlParameters from the command object, so they can be used again
                        Command.Parameters.Clear();

                        return Set;
                    }
                }
            }
            catch 
            {
                throw;
            }
        }

        /// <summary>
        /// 带事务的批量导入
        /// </summary>
        /// <param name="connectionstring"></param>
        /// <param name="dataTable"></param>
        /// <param name="transaction"></param>
        /// <param name="BatchSize"></param>
        /// <param name="BulkCopyTimeout"></param>
        /// <param name="copyOptions"></param>
        /// <returns></returns>
        internal static bool SQLBulkCopy(string connectionstring, DataTable dataTable, SqlTransaction transaction, int batchSize = 10000, int bulkCopyTimeout = 120, SqlBulkCopyOptions copyOptions = SqlBulkCopyOptions.Default)
        {
            if (dataTable == null || dataTable.Rows.Count == 0)
            {
                return false;
            }
            try
            {
                using (SqlConnection Connection = new SqlConnection(connectionstring))
                {
                    if (Connection.State != ConnectionState.Open)
                    {
                        Connection.Open();
                    }

                    using (SqlBulkCopy BulkCopy = new SqlBulkCopy(Connection, copyOptions, transaction))
                    {
                        BulkCopy.BatchSize = batchSize;
                        BulkCopy.BulkCopyTimeout = bulkCopyTimeout;
                        BulkCopy.DestinationTableName = dataTable.TableName;

                        for (int i = 0; i < dataTable.Columns.Count; i++)
                        {
                            BulkCopy.ColumnMappings.Add(dataTable.Columns[i].ColumnName, dataTable.Columns[i].ColumnName);
                        }

                        BulkCopy.WriteToServer(dataTable);
                    }
                }
                return true;
            }
            catch 
            {
                throw;
            }
        }

        /// <summary>
        /// 批量导入
        /// </summary>
        /// <param name="connectionstring"></param>
        /// <param name="dataTable"></param>
        /// <param name="BatchSize"></param>
        /// <param name="BulkCopyTimeout"></param>
        /// <param name="copyOptions"></param>
        /// <returns></returns>
        internal static bool SQLBulkCopy(string connectionstring, DataTable dataTable, int batchSize = 10000, int bulkCopyTimeout = 60, SqlBulkCopyOptions copyOptions = SqlBulkCopyOptions.Default)
        {
            if (dataTable == null || dataTable.Rows.Count == 0)
            {
                return false;
            }
            try
            {
                using (SqlBulkCopy BulkCopy = new SqlBulkCopy(connectionstring, copyOptions))
                {
                    BulkCopy.BatchSize = batchSize;
                    BulkCopy.BulkCopyTimeout = bulkCopyTimeout;
                    BulkCopy.DestinationTableName = dataTable.TableName;

                    for (int i = 0; i < dataTable.Columns.Count; i++)
                    {
                        BulkCopy.ColumnMappings.Add(dataTable.Columns[i].ColumnName, dataTable.Columns[i].ColumnName);
                    }

                    BulkCopy.WriteToServer(dataTable);
                }
                return true;
            }
            catch 
            {
                throw;
            }
        }
    }
}
