using Common;
using log4net;
using DocumentManageModel;
using System.Collections.Generic;
using System.Data;
using System.Reflection;
using System;

namespace DocumentManageDAL
{
    public class DocumentInterfaceDAL
    {
        ILog _log = LogManager.GetLogger(MethodBase.GetCurrentMethod().DeclaringType);

        #region Read

        /// <summary>
        /// 查询接口程序列表
        /// </summary>
        /// <param name="model"></param>
        /// <param name="total"></param>
        /// <returns></returns>
        public List<InterfaceProgramModel> QueryInterfaceProgramList(JQGridModel model, ref int total)
        {
            try
            {
                string SQLString = "SELECT ProgramID,ProgramName,SvnUrl,OrderIndex,CreateTime,UpdateTime FROM dbo.InterfaceProgram WHERE IsDelete=0";

                MSSQLDataBaseServer SQLHelper = new MSSQLDataBaseServer("DocumentManageDB");

                DataTable Result = SQLHelper.ExecutePageDatatable(SQLString, model.Page, model.Rows, "OrderIndex", model.OrderMethod);

                total = int.Parse(Result.ExtendedProperties["Total"].ToString());

                return Result.ToList<InterfaceProgramModel>();
            }
            catch (Exception e)
            {
                _log.Error(e.Message);
                return null;
            }
        }

        /// <summary>
        /// 查询接口列表
        /// </summary>
        /// <param name="model"></param>
        /// <param name="programID"></param>
        /// <param name="total"></param>
        /// <returns></returns>
        public List<InterfaceItemModel> QueryInterfaceItemList(JQGridModel model, int programID, ref int total)
        {
            try
            {
                string SQLString = string.Format("SELECT InterfaceID,Title,Path,Method,OrderIndex FROM dbo.InterfaceItem WHERE IsDelete=0 AND ProgramID={0}", programID);

                MSSQLDataBaseServer SQLHelper = new MSSQLDataBaseServer("DocumentManageDB");

                DataTable Result = SQLHelper.ExecutePageDatatable(SQLString, model.Page, model.Rows, "OrderIndex", model.OrderMethod);

                total = int.Parse(Result.ExtendedProperties["Total"].ToString());

                return Result.ToList<InterfaceItemModel>();
            }
            catch (Exception e)
            {
                _log.Error(e.Message);
                return null;
            }
        }

        /// <summary>
        /// 查询接口参数列表
        /// </summary>
        /// <param name="model"></param>
        /// <param name="programID"></param>
        /// <param name="total"></param>
        /// <returns></returns>
        public List<InterfaceParameterModel> QueryInterfaceParameterList(JQGridModel model, int interfaceID, ref int total)
        {
            try
            {
                string SQLString = string.Format("SELECT ParameterID,InterfaceID,ProgramID,ParameterType,ParameterCode,ParameterName,CheckContent,DataType,Remark,OrderIndex FROM dbo.InterfaceParameter WHERE IsDelete=0 AND InterfaceID={0}", interfaceID);

                MSSQLDataBaseServer SQLHelper = new MSSQLDataBaseServer("DocumentManageDB");

                DataTable Result = SQLHelper.ExecutePageDatatable(SQLString, model.Page, model.Rows, "OrderIndex", model.OrderMethod);

                total = int.Parse(Result.ExtendedProperties["Total"].ToString());

                return Result.ToList<InterfaceParameterModel>();
            }
            catch (Exception e)
            {
                _log.Error(e.Message);
                return null;
            }
        }

        /// <summary>
        /// 根据参数编号ID获取接口参数对象
        /// </summary>
        /// <param name="parameterID"></param>
        /// <returns></returns>
        public InterfaceParameterModel SelectInterfaceParameterModel(int parameterID)
        {
            try
            {
                string SQLString = @"SQL\DocumentInterface\SelectDocumentInterfaceParameter.sql".ToFileContent(parameterID);

                MSSQLDataBaseServer SQLHelper = new MSSQLDataBaseServer("DocumentManageDB");

                DataSet DataTableSet = SQLHelper.ExecuteDataset(SQLString);

                if (DataTableSet == null || DataTableSet.Tables.Count < 1)
                    return null;

                InterfaceParameterModel Model = new InterfaceParameterModel();

                List<InterfaceParameterModel> List = DataTableSet.Tables[0].ToList<InterfaceParameterModel>();

                if (List == null || List.Count != 1)
                    return null;
                else
                    Model = List[0];

                return Model;
            }
            catch
            {
                throw;
            }
        }

        /// <summary>
        /// 根据接口编号ID获取接口文档对象（不包含参数集合）
        /// </summary>
        /// <param name="parameterID"></param>
        /// <returns></returns>
        public InterfaceItemModel SelectDocumentInterfaceItem(int interfaceID)
        {
            try
            {
                string SQLString = @"SQL\DocumentInterface\SelectDocumentInterfaceItem.sql".ToFileContent(interfaceID);

                MSSQLDataBaseServer SQLHelper = new MSSQLDataBaseServer("DocumentManageDB");

                DataSet DataTableSet = SQLHelper.ExecuteDataset(SQLString);

                if (DataTableSet == null || DataTableSet.Tables.Count < 1)
                    return null;

                InterfaceItemModel Model = new InterfaceItemModel();

                List<InterfaceItemModel> List = DataTableSet.Tables[0].ToList<InterfaceItemModel>();

                if (List == null || List.Count != 1)
                    return null;
                else
                    Model = List[0];

                return Model;
            }
            catch
            {
                throw;
            }
        }

        /// <summary>
        /// 根据接口编号ID获取接口文档对象（包含参数集合）
        /// </summary>
        /// <param name="interfaceID"></param>
        /// <returns></returns>
        public InterfaceItemModel SearchDocumentInterfaceItem(int interfaceID)
        {
            try
            {
                string SQLString = @"SQL\DocumentInterface\SearchDocumentInterfaceItem.sql".ToFileContent(interfaceID);

                MSSQLDataBaseServer SQLHelper = new MSSQLDataBaseServer("DocumentManageDB");

                DataSet DataTableSet = SQLHelper.ExecuteDataset(SQLString);

                if (DataTableSet == null || DataTableSet.Tables.Count < 1)
                    return null;

                InterfaceItemModel Model = new InterfaceItemModel();

                List<InterfaceItemModel> InterfaceItemList = DataTableSet.Tables[0].ToList<InterfaceItemModel>();
                List<InterfaceParameterModel> InterfaceParameterList = DataTableSet.Tables[1].ToList<InterfaceParameterModel>();

                if (InterfaceParameterList == null)
                    InterfaceParameterList = new List<InterfaceParameterModel>();

                if (InterfaceItemList == null || InterfaceItemList.Count != 1)
                    return null;
                else
                    Model = InterfaceItemList[0];

                Model.InputParameter = InterfaceParameterList.FindAll(p => p.ParameterType == 1);
                Model.OutputParameter = InterfaceParameterList.FindAll(p => p.ParameterType == 2);

                return Model;
            }
            catch
            {
                throw;
            }
        }

        /// <summary>
        /// 根据接口程序ID获取接口程序（不包含接口文档）
        /// </summary>
        /// <param name="parameterID"></param>
        /// <returns></returns>
        public InterfaceProgramModel SelectDocumentInterfaceProgram(int programID)
        {
            try
            {
                string SQLString = @"SQL\DocumentInterface\SelectDocumentInterfaceProgram.sql".ToFileContent(programID);

                MSSQLDataBaseServer SQLHelper = new MSSQLDataBaseServer("DocumentManageDB");

                DataSet DataTableSet = SQLHelper.ExecuteDataset(SQLString);

                if (DataTableSet == null || DataTableSet.Tables.Count < 1)
                    return null;

                InterfaceProgramModel Model = new InterfaceProgramModel();

                List<InterfaceProgramModel> List = DataTableSet.Tables[0].ToList<InterfaceProgramModel>();

                if (List == null || List.Count != 1)
                    return null;
                else
                    Model = List[0];

                return Model;
            }
            catch
            {
                throw;
            }
        }

        /// <summary>
        /// 根据接口程序ID获取接口程序（包含接口文档）
        /// </summary>
        /// <param name="ProgramID"></param>
        /// <returns></returns>
        public InterfaceProgramModel SearchDocumentInterfaceProgram(int programID)
        {
            try
            {
                string SQLString = @"SQL\DocumentInterface\SearchDocumentInterfaceProgram.sql".ToFileContent(programID);

                MSSQLDataBaseServer SQLHelper = new MSSQLDataBaseServer("DocumentManageDB");

                DataSet DataTableSet = SQLHelper.ExecuteDataset(SQLString);

                if (DataTableSet == null || DataTableSet.Tables.Count < 1)
                    return null;

                InterfaceProgramModel Model = new InterfaceProgramModel();

                List<InterfaceProgramModel> InterfaceProgramList = DataTableSet.Tables[0].ToList<InterfaceProgramModel>();
                List<InterfaceItemModel> InterfaceItemList = DataTableSet.Tables[1].ToList<InterfaceItemModel>();
                List<InterfaceParameterModel> InterfaceParameterList = DataTableSet.Tables[2].ToList<InterfaceParameterModel>();

                if (InterfaceParameterList == null)
                {
                    InterfaceParameterList = new List<InterfaceParameterModel>();
                }

                if (InterfaceProgramList == null || InterfaceProgramList.Count != 1)
                {
                    return null;
                }  
                else
                {
                    Model = InterfaceProgramList[0];
                }             

                Model.InterfaceItemList = new List<InterfaceItemModel>();

                if (InterfaceItemList != null && InterfaceItemList.Count > 0)
                {
                    InterfaceItemList.ForEach(o =>
                    {
                        Model.InterfaceItemList.Add(o);
                        o.InputParameter = new List<InterfaceParameterModel>();
                        o.OutputParameter = new List<InterfaceParameterModel>();
                        o.InputParameter.AddRange(InterfaceParameterList.FindAll(x => x.ParameterType == 1 && x.InterfaceID == o.InterfaceID));
                        o.OutputParameter.AddRange(InterfaceParameterList.FindAll(y => y.ParameterType == 2 && y.InterfaceID == o.InterfaceID));
                    });
                }
            

                return Model;
            }
            catch
            {
                throw;
            }
        }

        #endregion

        #region Write

        #region 接口程序

        /// <summary>
        /// 新增一个接口程序
        /// </summary>
        /// <param name="model"></param>
        /// <returns></returns>
        public bool InsertSingleInterfaceProgramModel(InterfaceProgramModel model)
        {
            try
            {
                string Value = string.Format("'{0}',0,{2},N'{1}',GETDATE()", model.SvnUrl, model.ProgramName, model.OrderIndex);

                string SQLString = @"SQL\DocumentInterface\InsertSingleInterfaceProgram.sql".ToFileContent(Value);

                MSSQLDataBaseServer SQLHelper = new MSSQLDataBaseServer("DocumentManageDB");

                int Result = SQLHelper.ExecuteNonQuery(SQLString);

                if (Result == 1)
                    return true;
                else
                    return false;
            }
            catch
            {
                throw;
            }
        }

        /// <summary>
        /// 更新一个接口程序
        /// </summary>
        /// <param name="model"></param>
        /// <returns></returns>
        public bool UpdateSingleInterfaceProgramModel(InterfaceProgramModel model)
        {
            try
            {
                string SQLString = @"SQL\DocumentInterface\UpdateSingleInterfaceProgram.sql".ToFileContent(
                    model.ProgramID, model.ProgramName, model.OrderIndex, model.SvnUrl);

                MSSQLDataBaseServer SQLHelper = new MSSQLDataBaseServer("DocumentManageDB");

                int Result = SQLHelper.ExecuteNonQuery(SQLString);

                if (Result == 1)
                    return true;
                else
                    return false;
            }
            catch
            {
                throw;
            }
        }

        /// <summary>
        /// 删除一个接口程序
        /// </summary>
        /// <param name="model"></param>
        /// <returns></returns>
        public bool DeleteSingleInterfaceProgramModel(InterfaceProgramModel model)
        {
            try
            {
                string SQLString = @"SQL\DocumentInterface\DeleteSingleInterfaceProgram.sql".ToFileContent(model.ProgramID);

                MSSQLDataBaseServer SQLHelper = new MSSQLDataBaseServer("DocumentManageDB");

                int Result = SQLHelper.ExecuteNonQuery(SQLString);

                if (Result == 1)
                    return true;
                else
                    return false;
            }
            catch
            {
                throw;
            }
        }

        #endregion

        #region 接口文档

        /// <summary>
        /// 新增一个接口程序
        /// </summary>
        /// <param name="model"></param>
        /// <returns></returns>
        public bool InsertSingleInterfaceItemModel(InterfaceItemModel model)
        {
            try
            {
                string Value = string.Format("{0},N'{1}',N'{2}','{3}','{4}',N'{5}',N'{6}',GETDATE(),GETDATE(),0,{7},'{8}'",
                    model.ProgramID, model.Title, model.Info, model.Path, model.Method,
                    model.InputParameterJSON, model.OutputParameterJSON, model.OrderIndex, model.Code);

                string SQLString = @"SQL\DocumentInterface\InsertSingleInterfaceItem.sql".ToFileContent(Value);

                MSSQLDataBaseServer SQLHelper = new MSSQLDataBaseServer("DocumentManageDB");

                int Result = SQLHelper.ExecuteNonQuery(SQLString);

                if (Result == 1)
                    return true;
                else
                    return false;
            }
            catch
            {
                throw;
            }
        }

        /// <summary>
        /// 更新一个接口程序
        /// </summary>
        /// <param name="model"></param>
        /// <returns></returns>
        public bool UpdateSingleInterfaceItemModel(InterfaceItemModel model)
        {
            try
            {
                string SQLString = @"SQL\DocumentInterface\UpdateSingleInterfaceItem.sql".ToFileContent(
                    model.InterfaceID, model.Title, model.Info, model.Path, model.Method,
                    model.InputParameterJSON, model.OutputParameterJSON, model.OrderIndex, model.Code);

                MSSQLDataBaseServer SQLHelper = new MSSQLDataBaseServer("DocumentManageDB");

                int Result = SQLHelper.ExecuteNonQuery(SQLString);

                if (Result == 1)
                    return true;
                else
                    return false;
            }
            catch
            {
                throw;
            }
        }

        /// <summary>
        /// 删除一个接口程序
        /// </summary>
        /// <param name="model"></param>
        /// <returns></returns>
        public bool DeleteSingleInterfaceItemModel(InterfaceItemModel model)
        {
            try
            {
                string SQLString = @"SQL\DocumentInterface\DeleteSingleInterfaceItem.sql".ToFileContent(model.InterfaceID);

                MSSQLDataBaseServer SQLHelper = new MSSQLDataBaseServer("DocumentManageDB");

                int Result = SQLHelper.ExecuteNonQuery(SQLString);

                if (Result == 1)
                    return true;
                else
                    return false;
            }
            catch
            {
                throw;
            }
        }

        #endregion

        #region 接口参数

        /// <summary>
        /// 新增一个接口参数
        /// </summary>
        /// <param name="model"></param>
        /// <returns></returns>
        public bool InsertSingleInterfaceParameterModel(InterfaceParameterModel model)
        {
            try
            {
                string Value = string.Format("{0},{1},{2},0,{3},GETDATE(),GETDATE(),'{4}',N'{5}','{6}',N'{7}',N'{8}'",
                    model.ProgramID, model.InterfaceID, model.ParameterType, model.OrderIndex, model.ParameterCode,
                    model.ParameterName, model.DataType, model.CheckContent, model.Remark);

                string SQLString = @"SQL\DocumentInterface\InsertSingleInterfaceParameter.sql".ToFileContent(Value);

                MSSQLDataBaseServer SQLHelper = new MSSQLDataBaseServer("DocumentManageDB");

                int Result = SQLHelper.ExecuteNonQuery(SQLString);

                if (Result == 1)
                    return true;
                else
                    return false;
            }
            catch
            {
                throw;
            }
        }

        /// <summary>
        /// 更新一个接口参数
        /// </summary>
        /// <param name="model"></param>
        /// <returns></returns>
        public bool UpdateSingleInterfaceParameterModel(InterfaceParameterModel model)
        {
            try
            {
                string SQLString = @"SQL\DocumentInterface\UpdateSingleInterfaceParameter.sql".ToFileContent(
                    model.ParameterID, model.ParameterType, model.ParameterCode, model.ParameterName,
                    model.DataType, model.CheckContent, model.Remark, model.OrderIndex);

                MSSQLDataBaseServer SQLHelper = new MSSQLDataBaseServer("DocumentManageDB");

                int Result = SQLHelper.ExecuteNonQuery(SQLString);

                if (Result == 1)
                    return true;
                else
                    return false;
            }
            catch
            {
                throw;
            }
        }

        /// <summary>
        /// 删除一个接口参数
        /// </summary>
        /// <param name="model"></param>
        /// <returns></returns>
        public bool DeleteSingleInterfaceParameterModel(InterfaceParameterModel model)
        {
            try
            {
                string SQLString = @"SQL\DocumentInterface\DeleteSingleInterfaceParameter.sql".ToFileContent(model.ParameterID);

                MSSQLDataBaseServer SQLHelper = new MSSQLDataBaseServer("DocumentManageDB");

                int Result = SQLHelper.ExecuteNonQuery(SQLString);

                if (Result == 1)
                    return true;
                else
                    return false;
            }
            catch
            {
                throw;
            }
        }

        #endregion

        #endregion
    }
}
