using System;
using System.Data;
using System.Collections.Generic;
using Server;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace UnitTest
{
    [TestClass]
    public class ConvertTest
    {
        [TestMethod()]
        public void ToDataTableTest()
        {
            //arrange
            List<TestModel> List = new List<TestModel>();
            TestModel Model1 = new TestModel();
            Model1.ID = 1;
            Model1.Name = "周杰伦";
            Model1.Total = 91.0f;
            Model1.Date = DateTime.Now.AddYears(1).Date;
            Model1.IsEnable = true;
            List.Add(Model1);
            TestModel Model2 = new TestModel();
            Model2.ID = 2;
            Model2.Name = "蔡依林";
            Model2.Total = 39.01f;
            Model2.Date = DateTime.Now.AddYears(-1).Date;
            Model2.IsEnable = false;
            List.Add(Model2);

            //act
            DataTable Table = List.ToDataTable<TestModel>();

            //assert
            Assert.AreEqual(1, int.Parse(Table.Rows[0][0].ToString()));
            Assert.AreEqual("周杰伦", Table.Rows[0][1].ToString());
            Assert.AreEqual(91.0f, float.Parse(Table.Rows[0][2].ToString()));
            Assert.AreEqual(DateTime.Now.AddYears(1).Date, DateTime.Parse(Table.Rows[0][3].ToString()));
            Assert.AreEqual(true, bool.Parse(Table.Rows[0][4].ToString()));
            Assert.AreEqual(2, int.Parse(Table.Rows[1][0].ToString()));
            Assert.AreEqual("蔡依林", Table.Rows[1][1].ToString());
            Assert.AreEqual(39.01f, float.Parse(Table.Rows[1][2].ToString()));
            Assert.AreEqual(DateTime.Now.AddYears(-1).Date, DateTime.Parse(Table.Rows[1][3].ToString()));
            Assert.AreEqual(false, bool.Parse(Table.Rows[1][4].ToString()));
        }

        [TestMethod()]
        public void ToEntityTest()
        {
            //1、DataRow:空数据行异常，返回默认实体
            //2、DataRow:表结构和实体成员并非一一对应
            //3、Model:对象为空值
            //4、Model:实体成员与对象成员并非一一对应

            #region 1、空数据行异常，返回默认实体

            //arrange
            DataTable Table1 = new DataTable();
            Table1.Columns.AddRange(new DataColumn[]{  
                    new DataColumn("ID",typeof(int)),  
                    new DataColumn("Name",typeof(string)),  
                    new DataColumn("Total",typeof(float)),
                    new DataColumn("Date",typeof(DateTime)),  
                    new DataColumn("IsEnable",typeof(bool)),
                    new DataColumn("Extend",typeof(decimal))
                });
            DataRow Row1 = Table1.NewRow();

            //act
            TestEntity Entity1 = Row1.ToEntity<TestEntity>();

            //assert
            Assert.AreEqual(default(int), Entity1.ID);
            Assert.AreEqual(default(string), Entity1.Name);
            Assert.AreEqual(default(float), Entity1.Total);
            Assert.AreEqual(default(DateTime), Entity1.Date);
            Assert.AreEqual(default(bool), Entity1.IsEnable);
            Assert.AreEqual(default(decimal), Entity1.Extend);

            #endregion

            #region 2、表结构和实体成员并非一一对应

            //arrange
            DataTable Table2 = new DataTable();
            Table2.Columns.AddRange(new DataColumn[]{  
                    new DataColumn("ID",typeof(int)),  
                    new DataColumn("Name",typeof(string)),                    
                    new DataColumn("Date",typeof(DateTime)),  
                    new DataColumn("IsEnable",typeof(bool)),
                    new DataColumn("Extend",typeof(decimal)),
                    new DataColumn("Other",typeof(string))
                });
            DataRow Row2 = Table2.NewRow();
            Row2[0] = 1;
            Row2[1] = "用户1";
            Row2[2] = new DateTime(2015, 6, 1);
            Row2[3] = false;
            Row2[4] = 12.34;
            Row2[5] = "异常";

            //act
            TestEntity Entity2 = Row2.ToEntity<TestEntity>();

            //assert
            Assert.AreEqual(1, Entity2.ID);
            Assert.AreEqual("用户1", Entity2.Name);
            Assert.AreEqual(new DateTime(2015, 6, 1), Entity2.Date);
            Assert.AreEqual(false, Entity2.IsEnable);
            Assert.AreEqual((decimal)12.34, Entity2.Extend);
            Assert.AreEqual(default(float), Entity2.Total);

            #endregion

            #region 3、对象为空值

            //arrange
            TestModel Model3 = new TestModel();

            //act
            TestEntity Entity3 = Model3.ToEntity<TestModel, TestEntity>();

            //assert
            Assert.AreEqual(default(int), Entity3.ID);
            Assert.AreEqual(default(string), Entity3.Name);
            Assert.AreEqual(default(float), Entity3.Total);
            Assert.AreEqual(default(DateTime), Entity3.Date);
            Assert.AreEqual(default(bool), Entity3.IsEnable);
            Assert.AreEqual(default(decimal), Entity3.Extend);

            #endregion

            #region 4、实体成员与对象成员并非一一对应

            //arrange
            TestModel Model4 = new TestModel();
            Model4.ID = 11;
            Model4.Name = "刘亦菲";
            Model4.Total = 87.6f;
            Model4.IsEnable = true;
            Model4.Date = new DateTime(2014, 7, 1);
            Model4.Other = "异常";

            //act
            TestEntity Entity4 = Model4.ToEntity<TestModel, TestEntity>();

            //assert
            Assert.AreEqual(Model4.ID, Entity4.ID);
            Assert.AreEqual(Model4.Name, Entity4.Name);
            Assert.AreEqual(Model4.Total, Entity4.Total);
            Assert.AreEqual(Model4.IsEnable, Entity4.IsEnable);
            Assert.AreEqual(Model4.Date, Entity4.Date);
            Assert.AreEqual(default(decimal), Entity4.Extend);

            #endregion            
        }

        [TestMethod()]
        public void ToJsonTest()
        {
            //arrange
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

            //act
            string Result = Table.ToJson();

            //assert  
            string JsonString = "[{\"ID\":1,\"Name\":\"用户1\",\"Total\":12.34,\"IsEnable\":false},{\"ID\":2,\"Name\":\"用户2\",\"Total\":3.4,\"IsEnable\":true}]";
            Assert.AreEqual(JsonString, Result);
        }

        [TestMethod()]
        public void ToListTest()
        {
            //1、DataTable:数据表为空时
            //2、DataTable:数据表不为空

            #region 1、DataTable:数据表为空时

            //arrange
            DataTable Table1 = new DataTable();

            //act
            List<TestEntity> List1 = Table1.ToList<TestEntity>();

            //assert
            Assert.AreEqual(null, List1);

            #endregion

            #region 2、DataTable:数据表不为空

            //arrange
            DataTable Table2 = new DataTable();
            Table2.Columns.AddRange(new DataColumn[]{  
                new DataColumn("ID",typeof(int)),  
                new DataColumn("Name",typeof(string)),  
                new DataColumn("Total",typeof(float)),
                new DataColumn("Date",typeof(DateTime)),  
                new DataColumn("IsEnable",typeof(bool))      
            });
            DataRow Row1 = Table2.NewRow();
            Row1[0] = 1;
            Row1[1] = "用户1";
            Row1[2] = 12.34;
            Row1[3] = DateTime.Now.Date;
            Row1[4] = false;
            Table2.Rows.Add(Row1);
            DataRow Row2 = Table2.NewRow();
            Row2[0] = 2;
            Row2[1] = "用户2";
            Row2[2] = 3.4;
            Row2[3] = DateTime.Now.AddDays(2).Date;
            Row2[4] = true;
            Table2.Rows.Add(Row2);

            //act
            List<TestModel> List2 = Table2.ToList<TestModel>();

            //assert
            Assert.AreEqual(1, List2[0].ID);
            Assert.AreEqual("用户1", List2[0].Name);
            Assert.AreEqual(12.34f, List2[0].Total);
            Assert.AreEqual(DateTime.Now.Date, List2[0].Date);
            Assert.AreEqual(false, List2[0].IsEnable);
            Assert.AreEqual(2, List2[1].ID);
            Assert.AreEqual("用户2", List2[1].Name);
            Assert.AreEqual(3.4f, List2[1].Total);
            Assert.AreEqual(DateTime.Now.AddDays(2).Date, List2[1].Date);
            Assert.AreEqual(true, List2[1].IsEnable);

            #endregion
        }
    }
}
