using Server;
using System;
using System.Data;

namespace Logic
{
    internal sealed class JQGServer
    {
        #region Field

        private JQGridServer _jQGridServer;

        #endregion

        #region Constructor

        internal JQGServer()
        {
            this._jQGridServer = new JQGridServer(new DBServer().DataBaseServer);
        }

        #endregion

        #region Method

        /// <summary>
        /// 获取分页的数据表
        /// </summary>
        /// <param name="model"></param>
        /// <returns></returns>
        public DataTable GetJQGridDataTable(JQGridModel model)
        {
            JQGridEntity Entity = model.ToEntity<JQGridModel, JQGridEntity>();
            return _jQGridServer.GetJQGridDataTable(Entity);
        }

        /// <summary>
        /// 获取分页的数据
        /// </summary>
        /// <param name="model"></param>
        /// <returns></returns>
        public String GetJQGridJson(JQGridModel model)
        {
            JQGridEntity Entity = model.ToEntity<JQGridModel, JQGridEntity>();
            return _jQGridServer.GetJQGridJson(Entity);
        }

        #endregion
    }
}
