using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Serialization;

namespace Common
{
    public static class StringExtension
    {
        /// <summary>
        /// 验证是否空字符串
        /// </summary>
        /// <param name="str">待验证字符串</param>
        /// <returns>是空返回true,非空返回false</returns>
        public static bool IsNullOrEmpty(this string str)
        {
            return string.IsNullOrEmpty(str);
        }

        /// <summary>
        /// String类型转换成Int类型
        /// </summary>
        /// <param name="str">原始值</param>
        /// <param name="defaultValue">转换失败默认值</param>
        /// <returns>int</returns>
        public static int ToInt(this string str, int defaultValue = 0)
        {
            if (string.IsNullOrEmpty(str))
                return defaultValue;

            int Result;
            if (int.TryParse(str, out Result))
                return Result;
            else
                return defaultValue;
        }

        /// <summary>
        /// String类型转换成Bool类型
        /// </summary>
        /// <param name="str">原始值</param>
        /// <param name="defaultValue">转换失败默认值</param>
        /// <returns>bool</returns>
        public static bool ToBool(this string str, bool defaultValue = false)
        {
            if (string.IsNullOrEmpty(str))
                return defaultValue;

            bool Result;
            if (bool.TryParse(str, out Result))
                return Result;
            else
                return defaultValue;
        }

        /// <summary>
        /// String类型转换成DateTime类型
        /// </summary>
        /// <param name="str">原始值</param>
        /// <param name="defaultValue">转换失败默认值</param>
        /// <returns>datetime</returns>
        public static DateTime ToDateTime(this string str, DateTime defaultValue)
        {
            if (string.IsNullOrEmpty(str))
                return defaultValue;

            DateTime Result;
            if (DateTime.TryParse(str, out Result))
                return Result;
            else
                return defaultValue;
        }

        /// <summary>
        /// 根据身份证字符串返回生日
        /// </summary>
        /// <param name="identityCard">身份证字符串</param>
        /// <returns>生日（yyyy-MM-dd）</returns>
        public static string ToBirthday(this string identityCard)
        {
            long identityNumber;
            if (identityCard.IsNullOrEmpty() || !long.TryParse(identityCard, out identityNumber))
                return string.Empty;

            switch (identityCard.Length)
            {
                case 18:
                    return string.Format("{0}-{1}-{2}", identityCard.Substring(6, 4),
               identityCard.Substring(10, 2), identityCard.Substring(12, 2));
                case 15:
                    return string.Format("19{0}-{1}-{2}", identityCard.Substring(6, 2),
               identityCard.Substring(8, 2), identityCard.Substring(10, 2));
                default:
                    return string.Empty;
            }
        }

        /// <summary>
        /// 根据身份证字符串识别性别
        /// </summary>
        /// <param name="identityCard"></param>
        /// <returns></returns>
        public static string ToSex(this string identityCard)
        {
            long identityNumber;
            if (identityCard.IsNullOrEmpty() || !long.TryParse(identityCard, out identityNumber))
                return string.Empty;

            int intSex;
            switch (identityCard.Length)
            {
                case 18:
                    intSex = identityCard.Substring(14, 3).ToInt();
                    break;
                case 15:
                    intSex = identityCard.Substring(12, 3).ToInt();
                    break;
                default:
                    return string.Empty;
            }
            return intSex % 2 == 0 ? "女" : "男";
        }

        /// <summary>
        /// 设置文件名称，获取文件内容
        /// </summary>
        /// <param name="fileName">文件名称</param>
        /// <param name="parameters">替换参数</param>
        /// <returns>返回文件内容</returns>
        public static string ToFileContent(this string fileName, params object[] parameters)
        {
            string Path = AppDomain.CurrentDomain.BaseDirectory + fileName;

            if (!File.Exists(Path))
                return string.Empty;

            using (StreamReader Reader = new StreamReader(Path))
            {
                string Content = Reader.ReadToEnd();
                return parameters.Length > 0 ? string.Format(Content, parameters) : Content;
            }
        }

        /// <summary>
        /// 流转对象
        /// </summary>
        /// <typeparam name="T"></typeparam>
        /// <param name="stream"></param>
        /// <returns></returns>
        public static T DeserializeXML<T>(this string content)
        {
            T obj = default(T);
            using (MemoryStream ms = new MemoryStream(Encoding.UTF8.GetBytes(content)))
            {
                obj = (T)new XmlSerializer(typeof(T)).Deserialize(new StreamReader(ms, Encoding.UTF8));
            }
            return obj;
        }
    }
}
