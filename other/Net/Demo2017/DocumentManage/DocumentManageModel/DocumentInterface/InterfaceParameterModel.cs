using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DocumentManageModel
{
    /// <summary>
    /// 接口参数
    /// </summary>
    public class InterfaceParameterModel
    {
        /// <summary>
        /// 参数编号ID
        /// </summary>
        public int ParameterID { get; set; }

        /// <summary>
        /// 所属接口程序ID
        /// </summary>
        public int ProgramID { get; set; }

        /// <summary>
        /// 所属接口文档ID
        /// </summary>
        public int InterfaceID { get; set; }

        /// <summary>
        /// 参数类型（1-入参；2-出参）
        /// </summary>
        public int ParameterType { get; set; }

        /// <summary>
        /// 参数代码
        /// </summary>
        public string ParameterCode { get; set; }

        /// <summary>
        /// 参数名称
        /// </summary>
        public string ParameterName { get; set; }

        /// <summary>
        /// 参数数据类型
        /// </summary>
        public string DataType { get; set; }

        /// <summary>
        /// 约束
        /// </summary>
        public string CheckContent { get; set; }

        /// <summary>
        /// 备注说明
        /// </summary>
        public string Remark { get; set; }

        /// <summary>
        /// 排序
        /// </summary>
        public int OrderIndex { get; set; }
    }
}
