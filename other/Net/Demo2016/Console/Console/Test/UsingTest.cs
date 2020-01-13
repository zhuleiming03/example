using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data;
using System.Data.SqlClient;
using System.Configuration;

namespace ConsoleDemo.Test
{
    public class UsingTest
    {
        public bool Test()
        {
            string ConnectString = @"Data Source=.;Initial Catalog=PostLoan;Persist Security Info=True;User ID=;Password=";
            string QueryText = "SELECT * FROM dbo.Business WHERE IsRepayment=0";

            bool Result = false;

            using (SqlConnection con = new SqlConnection(ConnectString))
            {
                con.Open();
                if (con.State == ConnectionState.Open)
                {
                    Result = true;
                }
            }          

            return Result;
        }
    }
}
