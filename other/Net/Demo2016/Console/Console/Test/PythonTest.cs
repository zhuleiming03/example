using IronPython.Hosting;
using Microsoft.Scripting.Hosting;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleDemo
{
    public class PythonTest
    {
        public static void test2()
        {
            var engine = Python.CreateEngine();
            var scope = engine.CreateScope();
            var source = engine.CreateScriptSourceFromString(
                "def Auto_SevenDay1(arg1, arg2):\n" +
                " if arg2 == None :  return 'true';\n" +
                " else :  return false\n" +
                "\n" );
            source.Execute(scope);

            var Auto_SevenDay1 = scope.GetVariable<Func<object, object, object>>("Auto_SevenDay1");

            Business bus = new Business() { LendingSideKey = "COMPANY/WX_CDWS_LENDING", PeriodKind = 1 };
            object result = Auto_SevenDay1(bus, null);
            Console.WriteLine(result);

            DeductInstruction ins = new DeductInstruction() { DeductInstructionID = 1 };

            Dictionary<string, Tuple<ScriptEngine, ScriptScope, ScriptSource>> scriptDic = new Dictionary<string, Tuple<ScriptEngine, ScriptScope, ScriptSource>>();
            Tuple<Business, DeductInstruction> tuple = Tuple.Create<Business, DeductInstruction>(bus, ins);
            dynamic context = source.Execute(scope);
            Tuple<ScriptEngine, ScriptScope, ScriptSource> script = Tuple.Create<ScriptEngine, ScriptScope, ScriptSource>(engine, scope, source);
            Func<dynamic, dynamic> result22 = script.Item2.GetVariable<Func<dynamic, dynamic>>("Auto_SevenDay1");
            Console.WriteLine(result22(tuple));

            Console.ReadLine();

        }

    }

    public class Business
    {
        public string LendingSideKey { get; set; }

        public int PeriodKind { get; set; }
    }

    public class DeductInstruction
    {
        public int DeductInstructionID { get; set; }
    }
}
