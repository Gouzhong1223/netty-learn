package org.gouzhong1223.springbootnettywebsocketdemo.service.impl;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.gouzhong1223.springbootnettywebsocketdemo.config.NettyConfig;
import org.gouzhong1223.springbootnettywebsocketdemo.service.PushService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description :
 * @Date : create by QingSong in 2020-07-09 5:52 下午
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.springbootnettywebsocketdemo.service.impl
 * @ProjectName : netty-learn
 * @Version : 1.0.0
 */
@Service
public class PushServiceImpl implements PushService {
    @Override
    public void pushMsgToOne(String userId, String msg) {
        ConcurrentHashMap<String, Channel> userChannelMap = NettyConfig.getUserChannelMap();
        Channel channel = userChannelMap.get(userId);
        channel.writeAndFlush(new TextWebSocketFrame(msg));
    }

    @Override
    public void pushMsgToAll(String msg) {
        NettyConfig.getChannelGroup().writeAndFlush(new TextWebSocketFrame(msg));
    }

}
