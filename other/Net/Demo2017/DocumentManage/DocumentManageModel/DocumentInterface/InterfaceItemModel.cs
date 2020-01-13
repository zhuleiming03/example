using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DocumentManageModel
{
    /// <summary>
    /// 接口文档
    /// </summary>
    public class InterfaceItemModel
    {
        /// <summary>
        /// 所属接口程序
        /// </summary>
        public int ProgramID { get; set; }

        /// <summary>
        /// 接口编号ID
        /// </summary>
        public int InterfaceID { get; set; }

        /// <summary>
        /// 接口名称
        /// </summary>
        public string Title { get; set; }

        /// <summary>
        /// 编码
        /// </summary>
        public string Code { get; set; }

        /// <summary>
        /// 接口描述
        /// </summary>
        public string Info { get; set; }

        /// <summary>
        /// 接口路径
        /// </summary>
        public string Path { get; set; }

        /// <summary>
        /// 接口调用方式
        /// </summary>
        public string Method { get; set; }

        /// <summary>
        /// 接口调用入参集合
        /// </summary>
        public List<InterfaceParameterModel> InputParameter { get; set; }

        /// <summary>
        /// 接口返回出参集合
        /// </summary>
        public List<InterfaceParameterModel> OutputParameter { get; set; }

        /// <summary>
        /// 接口调用入参实例
        /// </summary>
        public string InputParameterJSON { get; set; }

        /// <summary>
        /// 接口返回出参实例
        /// </summary>
        public string OutputParameterJSON { get; set; }

        /// <summary>
        /// 接口文档更新时间
        /// </summary>
        public DateTime UpdateTime { get; set; }

        /// <summary>
        /// 排序
        /// </summary>
        public int OrderIndex { get; set; }
    }
}
