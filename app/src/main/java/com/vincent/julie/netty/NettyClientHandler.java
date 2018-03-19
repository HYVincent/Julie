package com.vincent.julie.netty;


import android.content.Intent;
import android.util.Log;

import com.vincent.julie.base.MyApp;
import com.vincent.julie.netty.msg.BaseMsg;
import com.vincent.julie.netty.msg.LoginMsg;
import com.vincent.julie.netty.msg.MsgType;
import com.vincent.julie.netty.msg.PingMsg;
import com.vincent.julie.netty.msg.PushMsg;
import com.vincent.mylibrary.entity.EventMsg;
import com.vincent.mylibrary.util.DateUtils;
import com.vincent.mylibrary.util.EventUtil;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;

/**
 * @author 徐飞
 * @version 2016/02/25 14:11
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<BaseMsg> {
    //设置心跳时间  开始
    public static final int MIN_CLICK_DELAY_TIME = 1000 * 3;
    private long lastClickTime = 0;
    //设置心跳时间   结束

    private static final String TAG = NettyClientHandler.class.getSimpleName();
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            switch (e.state()) {
                case WRITER_IDLE:
                    long currentTime = System.currentTimeMillis();
                    if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
                        lastClickTime = System.currentTimeMillis();
                        PingMsg pingMsg = new PingMsg();
                        pingMsg.setType(MsgType.PING);
                        pingMsg.setPhoneNum(MyApp.user.getUser_phone());
                        ctx.writeAndFlush(pingMsg);
                        System.out.println("send ping to server----------"+ DateUtils.getDateString(DateUtils.DATE_FORMAT_ALL,System.currentTimeMillis())+"--------------");
                    }
                    break;
                default:
                    break;
            }
        }
    }

    //这里是断线要进行的操作
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.out.println("重连了。---------");
        NettyClientBootstrap.getInstance().startNetty();
    }

    //这里是出现异常的话要进行的操作
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        try {
            super.exceptionCaught(ctx, cause);
            System.out.println("出现异常了。。。。。。。。。。。。。");
            cause.printStackTrace();
//            BaseApplication.getApplication().stopService(new Intent(BaseApplication.getApplication(), NettyPushService.class));
        }catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "exceptionCaught: Netty is Excption，stop NettyService");
        }
    }

    //这里是接受服务端发送过来的消息
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, BaseMsg baseMsg) throws Exception {
        if(baseMsg == null){
            Log.d(TAG, "messageReceived: baseMsg is null");
            return;
        }
        switch (baseMsg.getType()) {
            case LOGIN:
                //向服务器发起登录
                LoginMsg loginMsg = new LoginMsg();
                loginMsg.setType(MsgType.LOGIN);
                loginMsg.setPhoneNum(MyApp.user.getUser_phone());
                loginMsg.setUserName(MyApp.user.getUser_name());
                channelHandlerContext.writeAndFlush(loginMsg);
                break;
            case PING:
                System.out.println("receive ping from server----------"+ DateUtils.getDateString(DateUtils.DATE_FORMAT_ALL,System.currentTimeMillis())+"--------------");
                break;
            case PUSH:
                PushMsg pushMsg = (PushMsg) baseMsg;
                if(pushMsg!=null){
                    Log.d(TAG, "messageReceived: "+pushMsg.getPhoneNum()+" "+String.valueOf(pushMsg.getData()));
                }else {
                    Log.d(TAG, "messageReceived: the pushMsg is null");
                }
                break;
            default:
                System.out.println("default..");
                break;
        }
        ReferenceCountUtil.release(baseMsg);
    }
}
