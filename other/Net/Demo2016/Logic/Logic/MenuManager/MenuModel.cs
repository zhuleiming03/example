using System;

namespace Logic
{
    public sealed class MenuModel
    {
        #region Field

        private Int32 _menuID;

        #endregion

        #region Property

        /// <summary>
        /// 菜单ID
        /// </summary>
        public Int32 MenuID
        {
            get
            {
                return _menuID;
            }
            set
            {
                if (value == default(Int32))
                {
                    throw (new Exception("没有发现菜单ID"));
                }
                else
                {
                    _menuID = value;
                }
            }
        }

        /// <summary>
        /// 菜单父ID
        /// </summary>
        public Int32 MenuParentID { get; set; }

        /// <summary>
        /// 菜单等级
        /// </summary>
        public Int32 MenuLevel { get; set; }

        /// <summary>
        /// 菜单排序项
        /// </summary>
        public Int32 MenuOrder { get; set; }

        /// <summary>
        /// 菜单有效性
        /// </summary>
        public Boolean IsEnable { get; set; }

        /// <summary>
        /// 菜单项名称
        /// </summary>
        public String MenuItem { get; set; }

        /// <summary>
        /// 菜单文件地址
        /// </summary>
        public String Url { get; set; }

        #endregion

    }
}
