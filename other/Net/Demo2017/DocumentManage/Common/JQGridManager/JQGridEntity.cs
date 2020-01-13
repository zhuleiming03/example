using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Common
{
    public sealed class JQGridEntity
    {
        #region Field

        private int _page;
        private int _row;
        private string _sidx;
        private string _sord;
        private string _tableName;
        private string _columnName;
        private string _condition;

        #endregion

        #region Property

        /// <summary>
        /// 当前页面序号
        /// </summary>
        public int Page
        {
            get { return _page; }
            set { _page = value > 0 ? value : 1; }
        }

        /// <summary>
        /// 页面显示的行数
        /// </summary>
        public int Rows
        {
            get { return _row; }
            set { _row = value > 0 ? value : 1; }
        }

        /// <summary>
        /// 排序的列名
        /// </summary>
        public string Sidx
        {
            get { return _sidx; }
            set
            {
                if (string.IsNullOrEmpty(value))
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
        public string Sord
        {
            get { return _sord; }
            set
            {
                if (!string.IsNullOrEmpty(value))
                {
                    string Value = value.ToUpper();
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
        public string TableName
        {
            get { return _tableName; }
            set
            {
                if (string.IsNullOrEmpty(value))
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
        public string ColumnName
        {
            get { return _columnName; }
            set { _columnName = string.IsNullOrEmpty(value) ? "*" : value; }
        }

        /// <summary>
        /// 筛选条件
        /// </summary>
        public string Condition
        {
            get { return _condition; }
            set { _condition = string.IsNullOrEmpty(value) ? " " : value; }
        }

        #endregion
    }
}
