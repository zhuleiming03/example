using Logic;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace PCWeb.Controllers
{
    public class MenuController : Controller
    {
        //
        // GET: /Menu/

        public ActionResult Index()
        {
            //return View("test");
            return View();
        }

        public string GetList(MenuJQGridModel model)
        {
            return new MenuServer().GetMenuList(model);
        }
    }
}
