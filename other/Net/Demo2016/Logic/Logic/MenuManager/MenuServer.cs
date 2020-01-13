using Server;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;

namespace Logic
{
    public sealed class MenuServer
    {
        #region Method

        /// <summary>
        /// 获取Menu表的所有数据
        /// </summary>
        /// <returns></returns>
        public DataTable GetMenuAllTableData()
        {
            String CommandText = "SELECT MenuID,MenuParentID,MenuLevel,MenuItem,MenuOrder,IsEnable,Url FROM dbo.SYS_Menu";
            DataTable Table = new DBServer().ExecuteDatatable(CommandText);
            return Table;
        }

        /// <summary>
        /// 获取Menu表的分页数据
        /// </summary>
        /// <param name="model"></param>
        /// <returns></returns>
        public DataTable GetMenuPageTableData(MenuJQGridModel model)
        {
            model.TableName = "dbo.SYS_Menu";
            model.ColumnName = "MenuID,MenuParentID,MenuLevel,MenuItem,MenuOrder,IsEnable,Url";
            model.Sidx = String.IsNullOrEmpty(model.Sidx) ? "MenuID" : model.Sidx;
            DataTable Table = new JQGServer().GetJQGridDataTable(model);
            return Table;
        }

        /// <summary>
        /// 获取Menu的分页对象集文本
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public String GetPageMenuModelListText(MenuJQGridModel model)
        {
            model.TableName = "dbo.SYS_Menu";
            model.ColumnName = "MenuID,MenuParentID,MenuLevel,MenuItem,MenuOrder,IsEnable,Url";
            model.Sidx = String.IsNullOrEmpty(model.Sidx) ? "MenuID" : model.Sidx;
            return new JQGServer().GetJQGridJson(model);
        }

        /// <summary>
        /// 获取MenuModel的全部对象集
        /// </summary>
        /// <returns></returns>
        public List<MenuModel> GetAllMenuModelList()
        {
            DataTable Table = GetMenuAllTableData();
            return Table.ToList<MenuModel>();
        }

        #endregion
    }
}
