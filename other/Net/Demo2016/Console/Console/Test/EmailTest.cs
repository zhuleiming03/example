using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Net;
using System.Net.Mail;
using System.Text;
using System.Xml;
using System.Xml.Serialization;

namespace ConsoleDemo
{
    public class EmailTest
    {
        public static void Test()
        {
            MailMessage Mail = new MailMessage();
            Mail.From = new MailAddress("");
            Mail.To.Add("");
            Mail.Subject = "Test1";
            Mail.IsBodyHtml = false;
            Mail.BodyEncoding = Encoding.UTF8;
            Mail.Body = "Hi World!";
            using (SmtpClient sc = new SmtpClient("", 25))
            {
                sc.Credentials = new NetworkCredential("", "");
                sc.Send(Mail);
            }
        }
    }

}
