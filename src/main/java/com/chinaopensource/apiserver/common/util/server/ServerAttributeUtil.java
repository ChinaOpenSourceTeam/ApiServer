package com.chinaopensource.apiserver.common.util.server;

import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 程序启动时获取服务的Port
 * create by lzl ON 2017/12/24
 */
@Component
public class ServerAttributeUtil implements ApplicationListener<EmbeddedServletContainerInitializedEvent>{

    private static EmbeddedServletContainerInitializedEvent event ;

    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent embeddedServletContainerInitializedEvent) {
        event = embeddedServletContainerInitializedEvent;
    }

    /**
     * 获取server启动时的端口号
     * @return
     */
    public static final int getPort(){
        return event.getEmbeddedServletContainer().getPort();
    }
}
