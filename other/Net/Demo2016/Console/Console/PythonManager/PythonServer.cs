using IronPython.Hosting;
using Microsoft.Scripting.Hosting;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleDemo
{
    public class PythonServer
    {

        public static string Test()
        {
            string AlgorithmPath = ConfigurationManager.AppSettings["AlgorithmPath"].ToString();
            string py = File.ReadAllText(AlgorithmPath + "consoleTest.py");
            var result = PythonHelper.ExcutePython("Test", py, "Test", DateTime.Now.ToString());
            return result;
        }


    }

    public class TestModel
    {
        public string startDate { get; set; }

    }

    public static class PythonHelper
    {
        //python存储地方
        static Dictionary<string, Tuple<ScriptEngine, ScriptScope, ScriptSource>> scriptDic = new Dictionary<string, Tuple<ScriptEngine, ScriptScope, ScriptSource>>();

        //第三方标准包路径
        public static string PythonPackage { get { return ConfigurationManager.AppSettings["PythonPackage"].ToString(); } }

        public static dynamic ExcutePython(string algorithm, string pyStr, string funName, dynamic parm)
        {
            //调用一参一返
            Tuple<ScriptEngine, ScriptScope, ScriptSource> script = GetPythonContext(algorithm, pyStr);
            Func<dynamic, dynamic> result = script.Item2.GetVariable<Func<dynamic, dynamic>>(funName);
            return result(parm);
        }

        private static Tuple<ScriptEngine, ScriptScope, ScriptSource> GetPythonContext(string algorithm, string pyStr)
        {
            //判断是否存在字典中
            if (!scriptDic.ContainsKey(algorithm))
            {
                //创建Python引擎，DLR 动态语言执行类，用于解析和执行动态脚本
                ScriptEngine engine = Python.CreateEngine();
                //设置第三方包路径
                List<string> Paths = new List<string>();
                Paths.Add(PythonPackage);
                engine.SetSearchPaths(Paths);
                //创建一个执行上下文，其中保存了环境及全局变量。宿主（Host）可以通过创建不同的 ScriptScope 来提供多个数据隔离的执行上下文。
                ScriptScope scope = engine.CreateScope();
                //操控动态语言代码的类型，我们可以编译（Compile）、读取（Read Code Lines）或运行（Execute）代码。
                ScriptSource source = engine.CreateScriptSourceFromString(pyStr);
                //运行输入脚本
                dynamic context = source.Execute(scope);
                Tuple<ScriptEngine, ScriptScope, ScriptSource> temp = Tuple.Create<ScriptEngine, ScriptScope, ScriptSource>(engine, scope, source);
                scriptDic[algorithm] = temp;
            }
            return scriptDic[algorithm];
        }

}




}
