using Tamir.SharpSsh.jsch;

namespace Server
{
    /// <summary>
    /// 登录验证信息
    /// 实现UserInfo接口，可重写
    /// </summary>
    public class SFTPUserInfoModel : UserInfo
    {
        private string _password;

        public virtual string getPassphrase() { return null; }

        public virtual string getPassword() { return _password; }

        public virtual void setPassword(string password) { _password = password; }

        public virtual bool promptPassphrase(string message) { return true; }

        public virtual bool promptPassword(string message) { return true; }

        public virtual bool promptYesNo(string message) { return true; }

        public virtual void showMessage(string message) { }
    }
}
