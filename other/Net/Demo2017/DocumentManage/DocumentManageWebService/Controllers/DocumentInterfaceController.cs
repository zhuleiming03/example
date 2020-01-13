using DocumentManageBLL;
using DocumentManageModel;
using System.Web.Mvc;
using System.Text;

namespace DocumentManageWebService.Controllers
{
    public class DocumentInterfaceController : Controller
    {
        // GET: InterFaceDoc
        public ActionResult Index()
        {
            return View();
        }

        public ActionResult Arbitrary()
        {
            ViewBag.ProgramID = 44;
            return View();
        }

        public ActionResult Test()
        {
            return View();
        }

        #region DocumentInterfaceEdit

        public ActionResult DocumentInterfaceEdit()
        {
            return View();
        }

        public string DocumentInterfaceParemterShow(int programID, int interfaceID, int paremterType)
        {
            string Result = new DocumentInterfaceBLL().GetInterfaceItemModel(programID, interfaceID, paremterType);
            return Result;
        }

        #endregion

        #region DocumentInterfaceProgramManage

        public ActionResult DocumentInterfaceProgramManage()
        {
            return View();
        }

        public string QueryDocumentInterfaceProgramList(JQGridModel model)
        {
            return new DocumentInterfaceBLL().QueryInterfaceProgramList(model);
        }

        public string QueryDocumentInterfaceProgram(int programID)
        {
            return new DocumentInterfaceBLL().QueryInterfaceProgram(programID);
        }

        public bool InsertDocumentInterfaceProgram(InterfaceProgramModel model)
        {
            return new DocumentInterfaceBLL().InsertInterfaceProgram(model);
        }

        public bool DeleteDocumentInterfaceProgram(int programID)
        {
            return new DocumentInterfaceBLL().DeleteInterfaceProgram(programID);
        }

        public bool UpdateDocumentInterfaceProgram(InterfaceProgramModel model)
        {
            return new DocumentInterfaceBLL().UpdateInterfaceProgram(model);
        }

        #endregion

        #region DocumentInterfaceItemManage

        public ActionResult DocumentInterfaceItemManage(int id)
        {
            ViewBag.ProgramID = id;
            return View();
        }

        public string QueryDocumentInterfaceItemList(JQGridModel model, int programID)
        {
            return new DocumentInterfaceBLL().QueryInterfaceItemList(model, programID);
        }

        public string QueryDocumentInterfaceItem(int interfaceID)
        {
            return new DocumentInterfaceBLL().QueryInterfaceItem(interfaceID);
        }

        public bool InsertDocumentInterfaceItem(InterfaceItemModel model)
        {
            return new DocumentInterfaceBLL().InsertInterfaceItem(model);
        }

        public bool DeleteDocumentInterfaceItem(int interfaceID)
        {
            return new DocumentInterfaceBLL().DeleteInterfaceItem(interfaceID);
        }

        public bool UpdateDocumentInterfaceItem(InterfaceItemModel model)
        {
            return new DocumentInterfaceBLL().UpdateInterfaceItem(model);
        }

        #endregion

        #region DocumentInterfaceParamterManage

        public ActionResult DocumentInterfaceParamterManage(int id)
        {
            ViewBag.interfaceID = id;
            return View();
        }

        public string QueryDocumentInterfaceParamterList(JQGridModel model, int interfaceID)
        {
            return new DocumentInterfaceBLL().QueryInterfaceParameterList(model, interfaceID);
        }

        public string QueryDocumentInterfaceParamter(int parameterID)
        {
            return new DocumentInterfaceBLL().QueryInterfaceParameter(parameterID);
        }

        public bool InsertDocumentInterfaceParamter(InterfaceParameterModel model)
        {
            return new DocumentInterfaceBLL().InsertInterfaceParameter(model);
        }

        public bool DeleteDocumentInterfaceParamter(int parameterID)
        {
            return new DocumentInterfaceBLL().DeleteInterfaceParameter(parameterID);
        }

        public bool UpdateDocumentInterfaceParamter(InterfaceParameterModel model)
        {
            return new DocumentInterfaceBLL().UpdateInterfaceParameter(model);
        }

        #endregion
    }
}