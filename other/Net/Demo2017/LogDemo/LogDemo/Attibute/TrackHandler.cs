using System;
using System.Runtime.Remoting.Messaging;

namespace LogDemo
{
    /// <summary>
    /// 代理器
    /// </summary>
    public sealed class TrackHandler : IMessageSink
    {
        private IMessageSink _nextSink;

        private LogHelper _logger;

        public TrackHandler(IMessageSink nextSink)
        {
            Console.WriteLine("初始化 代理函数");
            _nextSink = nextSink;
            _logger = new LogHelper();
        }

        public IMessageSink NextSink
        {
            get { return _nextSink; }
        }

        /// <summary>
        /// 同步处理方法
        /// </summary>
        /// <param name="msg"></param>
        /// <returns></returns>
        public IMessage SyncProcessMessage(IMessage msg)
        {

            IMessage message = null;

            //方法调用接口
            IMethodCallMessage callMessage = msg as IMethodCallMessage;

            //如果被调用的方法没打MyCalculatorMethodAttribute标签
            if (callMessage == null || (Attribute.GetCustomAttribute(callMessage.MethodBase, typeof(TrackMethodAttribute))) == null)
            {
                message = _nextSink.SyncProcessMessage(msg);
            }
            else
            {
                PreProceed(msg);
                message = _nextSink.SyncProcessMessage(msg);
                PostProceed(message);
            }

            return message;
        }

        /// <summary>
        /// 异步处理方法
        /// </summary>
        /// <param name="msg"></param>
        /// <param name="replySink"></param>
        /// <returns></returns>
        public IMessageCtrl AsyncProcessMessage(IMessage msg, IMessageSink replySink)
        {
            Console.WriteLine("异步处理方法...");
            return null;
        }

        /// <summary>
        /// 方法执行前
        /// </summary>
        /// <param name="msg"></param>
        public void PreProceed(IMessage msg)
        {
            IMethodMessage message = (IMethodMessage)msg;

            _logger.Track(string.Format("Method <{0}> Total of <{1}> Parameters Including:", message.MethodName, message.ArgCount));

            for (int i = 0; i < message.ArgCount; i++)
            {
                string param = message.Args[i].ToJson();

                _logger.Track(string.Format("Parameter-{0} ：<{1}>", i + 1, param));
            }
        }

        /// <summary>
        /// 方法执行后
        /// </summary>
        /// <param name="msg"></param>
        public void PostProceed(IMessage msg)
        {
            IMethodReturnMessage message = (IMethodReturnMessage)msg;

            string param = message.ReturnValue.ToJson();

            _logger.Track(string.Format("Method <{1}> Return Value Of This Method Is <{0}>", param, message.MethodName));
        }
    }
}
