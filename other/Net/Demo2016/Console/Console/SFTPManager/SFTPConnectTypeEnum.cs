namespace Server
{
    public enum SFTPConnectTypeEnum
    {
        /// <summary>
        /// 先密钥方式连接，不成功则换密码方式连接
        /// </summary>
        FirstPrivateKeyThenPassword = 0,
        /// <summary>
        /// 通过密钥方式连接
        /// </summary>
        PrivateKey = 1,
        /// <summary>
        /// 通过密码方式连接
        /// </summary>
        Password = 2,
        /// <summary>
        /// 先密码方式连接，不成功则换密钥方式连接
        /// </summary>
        FirstPasswordThenPrivateKey = 3
    }
}
