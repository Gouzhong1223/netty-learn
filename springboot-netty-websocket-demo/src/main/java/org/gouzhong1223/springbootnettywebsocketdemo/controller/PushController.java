package org.gouzhong1223.springbootnettywebsocketdemo.controller;

import org.gouzhong1223.springbootnettywebsocketdemo.service.PushService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description :
 * @Date : create by QingSong in 2020-07-09 5:53 下午
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.springbootnettywebsocketdemo.controller
 * @ProjectName : netty-learn
 * @Version : 1.0.0
 */
@RestController
@RequestMapping("/push")
public class PushController {


    private final PushService pushService;

    public PushController(PushService pushService) {
        this.pushService = pushService;
    }

    /**
     * 推送给所有用户
     *
     * @param msg
     */
    @PostMapping("/pushAll")
    public void pushToAll(@RequestParam("msg") String msg) {
        pushService.pushMsgToAll(msg);
    }

    /**
     * 推送给指定用户
     *
     * @param userId
     * @param msg
     */
    @PostMapping("/pushOne")
    public void pushMsgToOne(@RequestParam("userId") String userId, @RequestParam("msg") String msg) {
        pushService.pushMsgToOne(userId, msg);
    }

}
