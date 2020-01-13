using Logic;
using System;
using System.Web.Mvc;

namespace PCWeb.Controllers
{
    public class MenuController : Controller
    {
        //
        // GET: /Menu/

        public ActionResult Index()
        {
            return View();
        }

        public String GetList(MenuJQGridModel model)
        {
            return new MenuServer().GetPageMenuModelListText(model);
        }
    }
}
