using System;

namespace Logic
{
    public class JQGridModel
    {
        #region Field

        private Int32 _page;
        private Int32 _row;
        private String _sidx;
        private String _sord;
        private String _tableName;
        private String _columnName;
        private String _condition;

        #endregion

        #region Property

        /// <summary>
        /// 当前页面序号
        /// </summary>
        public Int32 Page
        {
            get { return _page; }
            set { _page = value > 0 ? value : 1; }
        }

        /// <summary>
        /// 页面显示的行数
        /// </summary>
        public Int32 Rows
        {
            get { return _row; }
            set { _row = value > 0 ? value : 1; }
        }

        /// <summary>
        /// 排序的列名
        /// </summary>
        public String Sidx
        {
            get { return _sidx; }
            set
            {
                if (String.IsNullOrEmpty(value))
                {
                    throw (new Exception("没有发现排序的列名"));
                }
                else
                {
                    _sidx = value;
                }
            }
        }

        /// <summary>
        /// 排序的方式
        /// </summary>
        public String Sord
        {
            get { return _sord; }
            set
            {
                if (!String.IsNullOrEmpty(value))
                {
                    String Value = value.ToUpper();
                    if (Value == "ASC" || Value == "DESC")
                    {
                        _sord = Value;
                    }
                    else
                    {
                        throw (new Exception("排序的方式有误"));
                    }
                }
                else
                {
                    throw (new Exception("没有发现排序的方式"));
                }
            }
        }

        /// <summary>
        /// 查询的表名或视图名
        /// </summary>
        public String TableName
        {
            get { return _tableName; }
            set
            {
                if (String.IsNullOrEmpty(value))
                {
                    throw (new Exception("没有发现查询的表名或视图名"));
                }
                else
                {
                    _tableName = value;
                }
            }
        }

        /// <summary>
        /// 查询的列名
        /// </summary>
        public String ColumnName
        {
            get { return _columnName; }
            set { _columnName = String.IsNullOrEmpty(value) ? "*" : value; }
        }

        /// <summary>
        /// 筛选条件
        /// </summary>
        public String Condition
        {
            get { return _condition; }
            set { _condition = String.IsNullOrEmpty(value) ? " " : value; }
        }

        #endregion
  
    }
}
