package com.lipop.wx.config;

import com.lipop.wx.handler.LogHandler;
import com.lipop.wx.handler.MsgHandler;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static me.chanjar.weixin.common.api.WxConsts.MassMsgType.TEXT;
import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType;

/**
 * @author zhonglunsheng
 * @Description
 * @create 2020-10-28 22:19
 */
@Configuration
public class WxMpConfiguration {

    @Autowired
    private LogHandler logHandler;

    @Autowired
    private MsgHandler msgHandler;


    @Bean
    public WxMpMessageRouter messageRouter(WxMpService wxMpService) {
        final WxMpMessageRouter newRouter = new WxMpMessageRouter(wxMpService);

        // 记录所有事件的日志 （异步执行）
        newRouter.rule().handler(logHandler).next();

        // 默认
        newRouter.rule().async(false).msgType(TEXT).handler(msgHandler).end();

        return newRouter;
    }

}