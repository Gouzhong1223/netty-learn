package org.gouzhong1223.springbootnettywebsocketdemo.service;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description :
 * @Date : create by QingSong in 2020-07-09 5:52 下午
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.springbootnettywebsocketdemo.service
 * @ProjectName : netty-learn
 * @Version : 1.0.0
 */
public interface PushService {
    /**
     * 推送给指定用户
     *
     * @param userId
     * @param msg
     */
    void pushMsgToOne(String userId, String msg);

    /**
     * 推送给所有用户
     *
     * @param msg
     */
    void pushMsgToAll(String msg);

}
