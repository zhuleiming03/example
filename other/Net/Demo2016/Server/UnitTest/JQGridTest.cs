using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Server;
using System.Data;
using System.Collections.Generic;

namespace UnitTest
{
    [TestClass]
    public class JQGridTest
    {
        [TestMethod()]
        public void GetJQGridDataTableTest()
        {
            //arrange
            IDataBaseServer DB = new Server.Fakes.StubIDataBaseServer()
            {
                ExecuteDatatableStringStringDictionaryOfStringObjectOutIDataParameterArrayCommandTypeIDbTransaction = (string connectionString, string commandText, out Dictionary<string, object> outParameters, IDataParameter[] commandParameters, CommandType commandType, IDbTransaction transaction) =>
                {
                    DataTable Table = new DataTable();
                    Table.Columns.AddRange(new DataColumn[]{  
                        new DataColumn("ID",typeof(int)),  
                        new DataColumn("Name",typeof(string)),  
                        new DataColumn("Total",typeof(float)),
                        new DataColumn("Date",typeof(DateTime)),  
                        new DataColumn("IsEnable",typeof(bool))      
                    });
                    DataRow Row1 = Table.NewRow();
                    Row1[0] = 1;
                    Row1[1] = "用户1";
                    Row1[2] = 12.34;
                    Row1[3] = DateTime.Today;
                    Row1[4] = false;
                    Table.Rows.Add(Row1);
                    DataRow Row2 = Table.NewRow();
                    Row2[0] = 2;
                    Row2[1] = "用户2";
                    Row2[2] = 3.4;
                    Row2[3] = DateTime.Today.AddDays(2);
                    Row2[4] = true;
                    Table.Rows.Add(Row2);
                    outParameters = new Dictionary<string, object>();
                    outParameters.Add("@Count", 2);
                    return Table;
                }
            };

            JQGridEntity Entity = new JQGridEntity();
            JQGridServer Server = new JQGridServer(DB, "");

            //act
            DataTable Result = Server.GetJQGridDataTable(Entity);
            int RowCount = Convert.ToInt32(Result.ExtendedProperties["ROWCOUNT"]);

            //assert
            Assert.AreEqual(2, Result.Rows.Count);
            Assert.AreEqual(1, int.Parse(Result.Rows[0][0].ToString()));
            Assert.AreEqual("用户1", Result.Rows[0][1].ToString());
            Assert.AreEqual(12.34f, float.Parse(Result.Rows[0][2].ToString()));
            Assert.AreEqual(DateTime.Today, DateTime.Parse(Result.Rows[0][3].ToString()));
            Assert.AreEqual(false, bool.Parse(Result.Rows[0][4].ToString()));
            Assert.AreEqual(2, int.Parse(Result.Rows[1][0].ToString()));
            Assert.AreEqual("用户2", Result.Rows[1][1].ToString());
            Assert.AreEqual(3.4f, float.Parse(Result.Rows[1][2].ToString()));
            Assert.AreEqual(DateTime.Today.AddDays(2), DateTime.Parse(Result.Rows[1][3].ToString()));
            Assert.AreEqual(true, bool.Parse(Result.Rows[1][4].ToString()));
        }

        [TestMethod()]
        public void GetJQGridDataTableTestNull()
        {
            //arrange
            IDataBaseServer DB = new Server.Fakes.StubIDataBaseServer()
            {
                ExecuteDatatableStringStringDictionaryOfStringObjectOutIDataParameterArrayCommandTypeIDbTransaction = (string connectionString, string commandText, out Dictionary<string, object> outParameters, IDataParameter[] commandParameters, CommandType commandType, IDbTransaction transaction) =>
                {
                    DataTable Table = new DataTable();
                    outParameters = new Dictionary<string, object>();
                    outParameters.Add("@Count", 0);
                    return Table;
                }
            };

            JQGridEntity Entity = new JQGridEntity();
            JQGridServer Server = new JQGridServer(DB, "");

            //act
            DataTable Result = Server.GetJQGridDataTable(Entity);
            int RowCount = Convert.ToInt32(Result.ExtendedProperties["ROWCOUNT"]);

            //assert
            Assert.AreEqual(0, Result.Rows.Count);
        }

        [TestMethod()]
        public void GetJQGridJsonTest()
        {
            //arrange
            IDataBaseServer DB = new Server.Fakes.StubIDataBaseServer()
            {
                ExecuteDatatableStringStringDictionaryOfStringObjectOutIDataParameterArrayCommandTypeIDbTransaction = (string connectionString, string commandText, out Dictionary<string, object> outParameters, IDataParameter[] commandParameters, CommandType commandType, IDbTransaction transaction) =>
                {
                    DataTable Table = new DataTable();
                    Table.Columns.AddRange(new DataColumn[]{  
                        new DataColumn("ID",typeof(int)),  
                        new DataColumn("Name",typeof(string)),  
                        new DataColumn("Total",typeof(float)),
                        new DataColumn("IsEnable",typeof(bool))      
                    });
                    DataRow Row1 = Table.NewRow();
                    Row1[0] = 1;
                    Row1[1] = "用户1";
                    Row1[2] = 12.34;
                    Row1[3] = false;
                    Table.Rows.Add(Row1);
                    DataRow Row2 = Table.NewRow();
                    Row2[0] = 2;
                    Row2[1] = "用户2";
                    Row2[2] = 3.4;
                    Row2[3] = true;
                    Table.Rows.Add(Row2);
                    outParameters = new Dictionary<string, object>();
                    outParameters.Add("@Count", 2);
                    return Table;
                }
            };

            JQGridEntity Entity = new JQGridEntity();
            Entity.Page = 1;
            Entity.Rows = 10;
            JQGridServer Server = new JQGridServer(DB, "");

            //act
            string Result = Server.GetJQGridJson(Entity);

            //assert
            string TargeString = "{\"total\":1,\"page\":1,\"records\":2,\"rows\":[{\"ID\":1,\"Name\":\"用户1\",\"Total\":12.34,\"IsEnable\":false},{\"ID\":2,\"Name\":\"用户2\",\"Total\":3.4,\"IsEnable\":true}]}";
            Assert.AreEqual(TargeString, Result);
        }
    }
}
