using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UnitTest
{
    internal sealed class TestModel
    {
        public int ID { get; set; }
        public string Name { get; set; }
        public float Total { get; set; }
        public DateTime Date { get; set; }
        public bool IsEnable { get; set; }
        public string Other { get; set; }
    }

    internal sealed class TestEntity
    {
        public int ID { get; set; }
        public string Name { get; set; }
        public float Total { get; set; }
        public DateTime Date { get; set; }
        public bool IsEnable { get; set; }
        public decimal Extend { get; set; }
    }
}
