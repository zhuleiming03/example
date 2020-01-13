using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DocumentManageModel
{
    /// <summary>
    /// 接口程序
    /// </summary>
    public class InterfaceProgramModel
    {
        /// <summary>
        /// 接口程序ID
        /// </summary>
        public int ProgramID { get; set; }

        /// <summary>
        /// 接口程序名称
        /// </summary>
        public string ProgramName { get; set; }

        /// <summary>
        /// 接口程序SVN路径
        /// </summary>
        public string SvnUrl { get; set; }

        /// <summary>
        /// 接口程序接口集合
        /// </summary>
        public List<InterfaceItemModel> InterfaceItemList { get; set; }

        /// <summary>
        /// 接口程序参数序列化
        /// </summary>
        public string JsonArry { get; set; }

        /// <summary>
        /// 排序
        /// </summary>
        public int OrderIndex { get; set; }

        /// <summary>
        /// 创建时间
        /// </summary>
        public DateTime CreateDate { get; set; }

        /// <summary>
        /// 更新时间
        /// </summary>
        public DateTime UpdateDate { get; set; }
    }
}
