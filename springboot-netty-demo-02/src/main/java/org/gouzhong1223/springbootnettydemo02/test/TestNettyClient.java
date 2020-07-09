package org.gouzhong1223.springbootnettydemo02.test;

import org.gouzhong1223.springbootnettydemo02.client.NettyClient;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description :
 * @Date : create by QingSong in 2020-07-10 12:51 上午
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.springbootnettydemo02.test
 * @ProjectName : netty-learn
 * @Version : 1.0.0
 */
public class TestNettyClient {

    public static void main(String[] args) {

        // 开启10条线程，每条线程就相当于一个客户端
        for (int i = 1; i <= 10; i++) {

            new Thread(new NettyClient("thread" + "--" + i)).start();
        }
    }
}
