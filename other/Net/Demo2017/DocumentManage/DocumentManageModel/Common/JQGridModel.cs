using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DocumentManageModel
{
    public class JQGridModel
    {
        #region Field

        private int _page;
        private int _rows;
        private string _sidx;
        private string _sord;

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
            get { return _rows; }
            set { _rows = value > 0 ? value : 1; }
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
        /// 排序的方式 true:升序 false:降序
        /// </summary>
        public bool OrderMethod { get; set; }

        /// <summary>
        /// 排序的方式
        /// </summary>
        public string Sord
        {
            get { return _sord; }
            set
            {
                if (value.ToUpper().Trim() == "ASC")
                {
                    OrderMethod = true;
                }
                else if (value.ToUpper().Trim() == "DESC")
                {
                    OrderMethod = false;
                }
                else
                {
                    throw (new Exception("排序方式有误"));
                }
            }
        }

        /// <summary>
        /// 总行数
        /// </summary>
        public int Total { get; set; }

        #endregion
    }
}
