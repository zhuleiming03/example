using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Common;
using System.Data.SqlClient;
using System.Runtime.CompilerServices;

[assembly: InternalsVisibleTo("UnitTest")]
namespace Server
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
        private static SqlCommand PrepareCommand(DbConnection connection, CommandType commandType, String commandText, IDataParameter[] commandParameters, IDbTransaction transaction)
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
        /// <param name="connectionString"></param>
        /// <param name="commandText"></param>
        /// <param name="commandParameters"></param>
        /// <param name="commandType"></param>
        /// <param name="transaction"></param>
        /// <returns></returns>
        internal static Int32 ExecuteNonQuery(String connectionString, String commandText, IDataParameter[] commandParameters = null, CommandType commandType = CommandType.Text, IDbTransaction transaction = null)
        {
            try
            {
                using (SqlConnection Connection = new SqlConnection(connectionString))
                {
                    SqlCommand Command = PrepareCommand(Connection, commandType, commandText, commandParameters, transaction);

                    Int32 ReturnValue = Command.ExecuteNonQuery();

                    // Detach the SqlParameters from the command object, so they can be used again
                    Command.Parameters.Clear();

                    return ReturnValue;
                }
            }
            catch(Exception e)
            {
                throw e;
            }
        }

        /// <summary>
        /// 带Out或Output的无返回执行
        /// </summary>
        /// <param name="connectionString"></param>
        /// <param name="commandText"></param>
        /// <param name="outParameters"></param>
        /// <param name="commandParameters"></param>
        /// <param name="commandType"></param>
        /// <param name="transaction"></param>
        /// <returns></returns>
        internal static Int32 ExecuteNonQuery(String connectionString, String commandText, out Dictionary<String, Object> outParameters, IDataParameter[] commandParameters, CommandType commandType = CommandType.StoredProcedure, IDbTransaction transaction = null)
        {
            outParameters = new Dictionary<String, Object>();

            try
            {
                using (SqlConnection Connection = new SqlConnection(connectionString))
                {
                    SqlCommand Command = PrepareCommand(Connection, commandType, commandText, commandParameters, transaction);

                    Int32 ReturnValue = Command.ExecuteNonQuery();

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
            catch (Exception e)
            {
                throw e;
            }
        }

        /// <summary>
        /// 返回第一行第一列的值
        /// </summary>
        /// <param name="connectionString"></param>
        /// <param name="commandText"></param>
        /// <param name="commandParameters"></param>
        /// <param name="commandType"></param>
        /// <param name="transaction"></param>
        /// <returns></returns>
        internal static Object ExecuteScalar(String connectionString, String commandText, IDataParameter[] commandParameters = null, CommandType commandType = CommandType.Text, IDbTransaction transaction = null)
        {
            try
            {
                using (SqlConnection Connection = new SqlConnection(connectionString))
                {
                    SqlCommand Command = PrepareCommand(Connection, commandType, commandText, commandParameters, transaction);

                    // Execute the command & return the results
                    Object ReturnValue = Command.ExecuteScalar();

                    // Detach the SqlParameters from the command object, so they can be used again
                    Command.Parameters.Clear();

                    return ReturnValue;
                }
            }
            catch (Exception e)
            {
                throw e;
            }
        }

        /// <summary>
        /// 返回查询表
        /// </summary>
        /// <param name="connectionString"></param>
        /// <param name="commandText"></param>
        /// <param name="commandParameters"></param>
        /// <param name="commandType"></param>
        /// <param name="transaction"></param>
        /// <returns></returns>
        internal static DataTable ExecuteDatatable(String connectionString, String commandText, IDataParameter[] commandParameters = null, CommandType commandType = CommandType.Text, IDbTransaction transaction = null)
        {
            try
            {
                using (SqlConnection Connection = new SqlConnection(connectionString))
                {
                    SqlCommand Command = PrepareCommand(Connection, commandType, commandText, commandParameters, transaction);

                    Command.CommandTimeout = 3600;

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
            catch (Exception e)
            {
                throw e;
            }
        }

        /// <summary>
        /// 带Out或Output的查询表
        /// </summary>
        /// <param name="connectionString"></param>
        /// <param name="commandText"></param>
        /// <param name="outParameters"></param>
        /// <param name="commandParameters"></param>
        /// <param name="commandType"></param>
        /// <param name="transaction"></param>
        /// <returns></returns>
        internal static DataTable ExecuteDatatable(String connectionString, String commandText, out Dictionary<String, Object> outParameters, IDataParameter[] commandParameters, CommandType commandType = CommandType.StoredProcedure, IDbTransaction transaction = null)
        {
            outParameters = new Dictionary<String, Object>();

            try
            {
                using (SqlConnection Connection = new SqlConnection(connectionString))
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
            catch (Exception e)
            {
                throw e;
            }
        }

        /// <summary>
        /// 返回查询集合
        /// </summary>
        /// <param name="connectionString"></param>
        /// <param name="commandText"></param>
        /// <param name="commandParameters"></param>
        /// <param name="commandType"></param>
        /// <param name="transaction"></param>
        /// <returns></returns>
        internal static DataSet ExecuteDataset(String connectionString, String commandText, IDataParameter[] commandParameters = null, CommandType commandType = CommandType.Text, IDbTransaction transaction = null)
        {
            try
            {
                using (SqlConnection Connection = new SqlConnection(connectionString))
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
            catch (Exception e)
            {
                throw e;
            }
        }

        /// <summary>
        /// 带事务的批量导入
        /// </summary>
        /// <param name="connectionString"></param>
        /// <param name="dataTable"></param>
        /// <param name="transaction"></param>
        /// <param name="BatchSize"></param>
        /// <param name="BulkCopyTimeout"></param>
        /// <param name="copyOptions"></param>
        /// <returns></returns>
        internal static Boolean SQLBulkCopy(String connectionString, DataTable dataTable, SqlTransaction transaction, Int32 batchSize = 10000, Int32 bulkCopyTimeout = 120, SqlBulkCopyOptions copyOptions = SqlBulkCopyOptions.Default)
        {
            if (dataTable == null || dataTable.Rows.Count == 0)
            {
                return false;
            }
            try
            {
                using (SqlConnection Connection = new SqlConnection(connectionString))
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

                        for (Int32 i = 0; i < dataTable.Columns.Count; i++)
                        {
                            BulkCopy.ColumnMappings.Add(dataTable.Columns[i].ColumnName, dataTable.Columns[i].ColumnName);
                        }

                        BulkCopy.WriteToServer(dataTable);
                    }
                }
                return true;
            }
            catch (Exception e)
            {
                throw e;
            }
        }

        /// <summary>
        /// 批量导入
        /// </summary>
        /// <param name="connectionString"></param>
        /// <param name="dataTable"></param>
        /// <param name="BatchSize"></param>
        /// <param name="BulkCopyTimeout"></param>
        /// <param name="copyOptions"></param>
        /// <returns></returns>
        internal static Boolean SQLBulkCopy(String connectionString, DataTable dataTable, Int32 batchSize = 10000, Int32 bulkCopyTimeout = 60, SqlBulkCopyOptions copyOptions = SqlBulkCopyOptions.Default)
        {
            if (dataTable == null || dataTable.Rows.Count == 0)
            {
                return false;
            }
            try
            {
                using (SqlBulkCopy BulkCopy = new SqlBulkCopy(connectionString, copyOptions))
                {
                    BulkCopy.BatchSize = batchSize;
                    BulkCopy.BulkCopyTimeout = bulkCopyTimeout;
                    BulkCopy.DestinationTableName = dataTable.TableName;

                    for (Int32 i = 0; i < dataTable.Columns.Count; i++)
                    {
                        BulkCopy.ColumnMappings.Add(dataTable.Columns[i].ColumnName, dataTable.Columns[i].ColumnName);
                    }

                    BulkCopy.WriteToServer(dataTable);
                }
                return true;
            }
            catch (Exception e)
            {
                throw e;
            }
        }
    }
}
