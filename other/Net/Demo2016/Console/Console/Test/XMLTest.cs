using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Text;
using System.Xml.Serialization;

namespace Server
{
    public class XMLTest
    {
        public static void Test()
        {
            IDataBaseServer DB = new MSSQLDataBaseServer();
            DB.ConnectString = ConfigurationManager.ConnectionStrings["DB"].ToString();

            string SQLString = "SELECT * FROM dbo.student FOR XML PATH('tr'),ROOT('table')";

            string Result = DB.ExecuteScalar(SQLString).ToString();

            table aa = XMLServer.XmlDeserialize<table>(Result, Encoding.UTF8);

            string xml = @"<table><Trs><id>1</id><name>张三</name></Trs><Trs><id>2</id><name>李四</name>
</Trs><Trs><id>3</id><name>王二麻子</name></Trs></table>";

            table bb = XMLServer.XmlDeserialize<table>(xml, Encoding.UTF8);

        }
    }

    public class table
    {
        public table()
        {
            tr = new List<tr>();
        }

        [XmlElement]
        public List<tr> tr { get; set; }
    }

    public class tr
    {
        [XmlElement]
        public student_id student_id { get; set; }

        [XmlElement]
        public student_name student_name { get; set; }
    }

    public class student_id
    {
        [XmlText]
        public string txt { get; set; }
    }

    public class student_name
    {
        [XmlText]
        public string txt { get; set; }
    }
}
