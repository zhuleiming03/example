using System;
using System.Runtime.Remoting.Contexts;
using System.Runtime.Remoting.Messaging;

namespace LogDemo
{
    [AttributeUsage(AttributeTargets.Class, AllowMultiple = true)]
    public sealed class TrackAttribute : ContextAttribute, IContributeObjectSink
    {
        public TrackAttribute()
            : base("Track")
        {
            Console.WriteLine("Track...构造函数");
        }


        /// <summary>
        /// 实现IContributeObjectSink接口当中的消息接收器接口
        /// </summary>
        /// <param name="obj"></param>
        /// <param name="nextSink"></param>
        /// <returns></returns>
        public IMessageSink GetObjectSink(MarshalByRefObject obj, IMessageSink nextSink)
        {
            return new TrackHandler(nextSink);
        }
    }
}
