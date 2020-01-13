using System;
using System.Data;
using System.Data.SqlClient;
using System.Collections.Generic;
using System.Xml;
using Server;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace UnitTest
{
    [TestClass()]
    public class MSSQLDBServerTest
    {
        string ConnectString = @"Data Source=SH-HK-ZHULM\DEMO;Initial Catalog=Demo;Persist Security Info=True;User ID=sa;Password=sa";

        [TestMethod()]
        public void TestSucceed()
        {
            //1、用户表插入一个员工并返回ID
            //2、查询用户表
            //3、批量导入10条角色数据
            //4、查询角色表并返回条数
            //5、查询用户表和角色表     
            //6、查询单值
            //7、删除用户
            //8、删除角色

            #region 1、用户表插入一个员工并返回ID

            SqlParameter[] ParameterStep1 = { 
                new SqlParameter("@UserID", SqlDbType.Int),
                new SqlParameter("@UserCode", SqlDbType.VarChar, 50),
                new SqlParameter("@UserName", SqlDbType.NVarChar, 50)
            };
            ParameterStep1[1].Value = "D001";
            ParameterStep1[2].Value = "管理员";
            ParameterStep1[0].Direction = ParameterDirection.Output;
            Dictionary<String, Object> OutParametersStep1;
            Int32 ResultStep1 = default(Int32);
            String OutValueStep1 = String.Empty;

            try
            {
                ResultStep1 = MSSQLDBServer.ExecuteNonQuery(ConnectString, "DemoOutParameterExecuteNonQuery", 
                    out OutParametersStep1, ParameterStep1);
                OutValueStep1 = OutParametersStep1["@UserID"].ToString();
            }
            catch
            {
                Assert.Fail();
            }

            #endregion

            #region 2、查询用户表

            SqlParameter[] ParameterStep2 = { new SqlParameter("@UserID", SqlDbType.VarChar, 50) };
            ParameterStep2[0].Value = OutValueStep1;
            String QueryTextStep2 = "SELECT * FROM dbo.SYS_User WHERE UserID=@UserID";
            DataTable ResultStep2 = default(DataTable);

            try
            {
                ResultStep2 = MSSQLDBServer.ExecuteDatatable(ConnectString, QueryTextStep2, ParameterStep2);
            }
            catch
            {
                Assert.Fail();
            }

            #endregion

            #region 3、批量导入10条角色数据

            DataTable ImportTable = new DataTable();
            ImportTable.Columns.AddRange(new DataColumn[]{
                new DataColumn("RoleCode",typeof(String)),
                new DataColumn("RoleName",typeof(String)),
                new DataColumn("RoleLevel",typeof(Int32)),
                new DataColumn("IsEnable",typeof(Boolean))
            });
            for (Int32 i = 0; i < 10; i++)
            {
                DataRow Row = ImportTable.NewRow();
                Row[0] = String.Format("T{0}", i.ToString());
                Row[1] = String.Format("测试{0}", i.ToString());
                Row[2] = -1;
                Row[3] = false;
                ImportTable.Rows.Add(Row);
            }
            ImportTable.TableName = "dbo.SYS_Role";
            Boolean ResultStep3 = default(Boolean);

            try
            {
                ResultStep3 = MSSQLDBServer.SQLBulkCopy(ConnectString, ImportTable);
            }
            catch
            {
                Assert.Fail();
            }

            #endregion

            #region 4、查询角色表并返回条数

            SqlParameter[] ParameterStep4 ={
                new SqlParameter("@OrderBy",SqlDbType.NVarChar,50),
                new SqlParameter("@OrderType",SqlDbType.NVarChar,10),
                new SqlParameter("@Tabel",SqlDbType.NVarChar,50),
                new SqlParameter("@ColumnName",SqlDbType.NVarChar,100),
                new SqlParameter("@Condition",SqlDbType.NVarChar,500),
                new SqlParameter("@Page",SqlDbType.Int),
                new SqlParameter("@Row",SqlDbType.Int),
                new SqlParameter("@Count",SqlDbType.Int)
            };
            ParameterStep4[0].Value = "RoleID";
            ParameterStep4[1].Value = "ASC";
            ParameterStep4[2].Value = "dbo.SYS_Role";
            ParameterStep4[3].Value = "*";
            ParameterStep4[4].Value = " AND RoleLevel=-1 ";
            ParameterStep4[5].Value = "2";
            ParameterStep4[6].Value = "5";
            ParameterStep4[7].Direction = ParameterDirection.Output;
            Dictionary<String, Object> OutParametersStep4;
            DataTable ResultStep4 = default(DataTable);
            String OutValueStep4 = String.Empty;

            try
            {
                ResultStep4 = MSSQLDBServer.ExecuteDatatable(ConnectString, "dbo.SP_PageQuery", 
                    out OutParametersStep4, ParameterStep4);
                OutValueStep4 = OutParametersStep4["@Count"].ToString();
            }
            catch
            {
                Assert.Fail();
            }

            #endregion

            #region 5、查询用户表和角色表

            String QueryTextStep5 = @"SELECT * FROM dbo.SYS_User WHERE UserID=@UserID;
                                      SELECT * FROM dbo.SYS_Role WHERE RoleLevel=-1";
            SqlParameter[] ParameterStep5 ={
                new SqlParameter("@UserID",SqlDbType.VarChar,50)
            };
            ParameterStep5[0].Value = OutValueStep1;
            DataSet ResultStep5 = default(DataSet);

            try
            {
                ResultStep5 = MSSQLDBServer.ExecuteDataset(ConnectString, QueryTextStep5, ParameterStep5);
            }
            catch
            {
                Assert.Fail();
            }

            #endregion

            #region 6、查询单值

            String QueryTextStep6 = @"SELECT RoleName FROM dbo.SYS_Role WHERE RoleCode=@RoleCode ";
            SqlParameter[] ParameterStep6 ={
                new SqlParameter("@RoleCode",SqlDbType.VarChar,50)
            };
            ParameterStep6[0].Value = "T5";
            String ResultStep6 = String.Empty;

            try
            {
                ResultStep6 = MSSQLDBServer.ExecuteScalar(ConnectString, QueryTextStep6, ParameterStep6).ToString();
            }
            catch
            {
                Assert.Fail();
            }

            #endregion

            #region 7、删除用户

            String QueryTextStep7 = @"DELETE dbo.SYS_User WHERE UserID=@UserID";
            SqlParameter[] ParameterStep7 ={
                new SqlParameter("@UserID",SqlDbType.Int)
            };
            ParameterStep7[0].Value = Int32.Parse(OutValueStep1);
            Int32 ResultStep7 = default(Int32);

            try
            {
                ResultStep7 = MSSQLDBServer.ExecuteNonQuery(ConnectString, QueryTextStep7, ParameterStep7);
            }
            catch
            {
                Assert.Fail();
            }

            #endregion

            #region 8、删除角色

            String QueryTextStep8 = @"DELETE dbo.SYS_Role WHERE RoleLevel='-1'";
            Int32 ResultStep8 = default(Int32);

            try
            {
                ResultStep8 = MSSQLDBServer.ExecuteNonQuery(ConnectString, QueryTextStep8);
            }
            catch
            {
                Assert.Fail();
            }

            #endregion

            //assert
            Assert.AreEqual(1, ResultStep1);
            Assert.AreEqual("D001", ResultStep2.Rows[0][1].ToString());
            Assert.AreEqual("管理员", ResultStep2.Rows[0][2].ToString());
            Assert.AreEqual(true, ResultStep3);
            Assert.AreEqual("测试6", ResultStep4.Rows[1][3].ToString());
            Assert.AreEqual("10", OutValueStep4);
            Assert.AreEqual(1, ResultStep5.Tables[0].Rows.Count);
            Assert.AreEqual(1, ResultStep5.Tables[0].Rows.Count);
            Assert.AreEqual("测试5", ResultStep6);
            Assert.AreEqual(1, ResultStep7);
            Assert.AreEqual(10, ResultStep8);
        }
    }
}
