using DocumentManageDAL;
using DocumentManageModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Runtime.Serialization;
using System.Threading.Tasks;
using System.Web.Script.Serialization;
using log4net;
using System.Reflection;

namespace DocumentManageBLL
{
    public class DocumentInterfaceBLL
    {

        ILog logger = LogManager.GetLogger(MethodBase.GetCurrentMethod().DeclaringType);

        /// <summary>
        /// 获取接口程序信息
        /// </summary>
        /// <returns></returns>
        public InterfaceProgramModel GetProductInterfaceModel()
        {
            InterfaceProgramModel Model = new DocumentInterfaceDAL().SearchDocumentInterfaceProgram(44);

            StringBuilder Json = new StringBuilder();
            foreach (InterfaceItemModel Item in Model.InterfaceItemList)
            {

                string JsonString = "{\"Title\":\"" + Item.Title + "\",\"InputParameterJSON\":" + Item.InputParameterJSON
                    + ",\"OutputParameterJSON\":" + Item.OutputParameterJSON + ",\"Method\":\"" + Item.Method + "\"},";

                Json.Append(JsonString);

            }
            string JsonResult = Json.ToString();
            JsonResult = JsonResult.Substring(0, JsonResult.Length - 1);
            JsonResult = string.Format("[{0}]", JsonResult);

            Model.JsonArry = JsonResult;

            return Model;
        }

        public string GetInterfaceItemModel(int programID, int interfaceID, int paremterType)
        {
            InterfaceItemModel Model = new DocumentInterfaceDAL().SearchDocumentInterfaceItem(interfaceID);

            string Result = string.Empty;

            if (Model != default(InterfaceItemModel))
            {
                JavaScriptSerializer Json = new JavaScriptSerializer();
                if (paremterType == 1)
                {
                    Result = Json.Serialize(Model.InputParameter);
                }
                else
                {
                    Result = Json.Serialize(Model.OutputParameter);
                }
            }

            return Result;
        }


        #region InterfaceProgramManage

        public string QueryInterfaceProgramList(JQGridModel model)
        {
            try
            {
                int Total = 0;
                model.Sidx = "OrderIndex";

                List<InterfaceProgramModel> List = new DocumentInterfaceDAL().QueryInterfaceProgramList(model, ref Total);

                //构造集合对象
                var JQGridContent = new
                {
                    total = Total,       //总页数
                    page = model.Page,                                 //当前页
                    records = model.Rows,                                 //查询出的记录数
                    rows = List                      //包含实际数据的数组
                };

                //将集合对象序列化
                string Result = new JavaScriptSerializer().Serialize(JQGridContent);

                return Result;
            }
            catch (Exception e)
            {
                logger.Error(e.ToString());
                return "";
            }
        }

        public string QueryInterfaceProgram(int programID)
        {
            string Result = string.Empty;
            try
            {
                InterfaceProgramModel Model = new DocumentInterfaceDAL().SearchDocumentInterfaceProgram(programID);
                Result = new JavaScriptSerializer().Serialize(Model);
            }
            catch (Exception e)
            {
                logger.Error(e.ToString());
            }

            return Result;
        }

        public bool InsertInterfaceProgram(InterfaceProgramModel model)
        {
            bool Result = false;
            try
            {
                Result = new DocumentInterfaceDAL().InsertSingleInterfaceProgramModel(model);
            }
            catch (Exception e)
            {
                logger.Error(e.ToString());
            }

            return Result;
        }

        public bool DeleteInterfaceProgram(int programID)
        {
            bool Result = false;
            try
            {
                InterfaceProgramModel model = new InterfaceProgramModel() { ProgramID = programID };
                Result = new DocumentInterfaceDAL().DeleteSingleInterfaceProgramModel(model);
            }
            catch (Exception e)
            {
                logger.Error(e.ToString());
            }

            return Result;
        }

        public bool UpdateInterfaceProgram(InterfaceProgramModel model)
        {
            bool Result = false;
            try
            {
                Result = new DocumentInterfaceDAL().UpdateSingleInterfaceProgramModel(model);
            }
            catch (Exception e)
            {
                logger.Error(e.ToString());
            }

            return Result;
        }

        #endregion

        #region InterfaceItemManage

        public string QueryInterfaceItemList(JQGridModel model, int programID)
        {
            try
            {
                int Total = 0;
                model.Sidx = "OrderIndex";

                List<InterfaceItemModel> List = new DocumentInterfaceDAL().QueryInterfaceItemList(model, programID, ref Total);

                //构造集合对象
                var JQGridContent = new
                {
                    total = Total,       //总页数
                    page = model.Page,                                 //当前页
                    records = model.Rows,                                 //查询出的记录数
                    rows = List                      //包含实际数据的数组
                };

                //将集合对象序列化
                string Result = new JavaScriptSerializer().Serialize(JQGridContent);

                return Result;
            }
            catch (Exception e)
            {
                logger.Error(e.ToString());
                return "";
            }
        }

        public string QueryInterfaceItem(int interfaceID)
        {
            string Result = string.Empty;
            try
            {
                InterfaceItemModel Model = new DocumentInterfaceDAL().SearchDocumentInterfaceItem(interfaceID);
                Result = new JavaScriptSerializer().Serialize(Model);
            }
            catch (Exception e)
            {
                logger.Error(e.ToString());
            }

            return Result;
        }

        public bool InsertInterfaceItem(InterfaceItemModel model)
        {
            bool Result = false;
            try
            {
                Result = new DocumentInterfaceDAL().InsertSingleInterfaceItemModel(model);
            }
            catch (Exception e)
            {
                logger.Error(e.ToString());
            }

            return Result;
        }

        public bool DeleteInterfaceItem(int interfaceID)
        {
            bool Result = false;
            try
            {
                InterfaceItemModel model = new InterfaceItemModel() { InterfaceID = interfaceID };
                Result = new DocumentInterfaceDAL().DeleteSingleInterfaceItemModel(model);
            }
            catch (Exception e)
            {
                logger.Error(e.ToString());
            }

            return Result;
        }

        public bool UpdateInterfaceItem(InterfaceItemModel model)
        {
            bool Result = false;
            try
            {
                Result = new DocumentInterfaceDAL().UpdateSingleInterfaceItemModel(model);
            }
            catch (Exception e)
            {
                logger.Error(e.ToString());
            }

            return Result;
        }

        #endregion

        #region InterfaceParamterManage

        public string QueryInterfaceParameterList(JQGridModel model, int interfaceID)
        {
            try
            {
                int Total = 0;
                model.Sidx = "OrderIndex";

                List<InterfaceParameterModel> List = new DocumentInterfaceDAL().QueryInterfaceParameterList(model, interfaceID, ref Total);

                //构造集合对象
                var JQGridContent = new
                {
                    total = Total,       //总页数
                    page = model.Page,                                 //当前页
                    records = model.Rows,                                 //查询出的记录数
                    rows = List                      //包含实际数据的数组
                };

                //将集合对象序列化
                string Result = new JavaScriptSerializer().Serialize(JQGridContent);

                return Result;
            }
            catch (Exception e)
            {
                logger.Error(e.ToString());
                return "";
            }
        }

        public string QueryInterfaceParameter(int parameterID)
        {
            string Result = string.Empty;
            try
            {
                InterfaceParameterModel Model = new DocumentInterfaceDAL().SelectInterfaceParameterModel(parameterID);
                Result = new JavaScriptSerializer().Serialize(Model);
            }
            catch (Exception e)
            {
                logger.Error(e.ToString());
            }

            return Result;
        }

        public bool InsertInterfaceParameter(InterfaceParameterModel model)
        {
            bool Result = false;
            try
            {
                Result = new DocumentInterfaceDAL().InsertSingleInterfaceParameterModel(model);
            }
            catch (Exception e)
            {
                logger.Error(e.ToString());
            }

            return Result;
        }

        public bool DeleteInterfaceParameter(int parameterID)
        {
            bool Result = false;
            try
            {
                InterfaceParameterModel model = new InterfaceParameterModel() { ParameterID = parameterID };
                Result = new DocumentInterfaceDAL().DeleteSingleInterfaceParameterModel(model);
            }
            catch (Exception e)
            {
                logger.Error(e.ToString());
            }

            return Result;
        }

        public bool UpdateInterfaceParameter(InterfaceParameterModel model)
        {
            bool Result = false;
            try
            {
                Result = new DocumentInterfaceDAL().UpdateSingleInterfaceParameterModel(model);
            }
            catch (Exception e)
            {
                logger.Error(e.ToString());
            }

            return Result;
        }

        #endregion

    }
}
