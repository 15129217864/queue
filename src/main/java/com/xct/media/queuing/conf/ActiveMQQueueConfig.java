package com.xct.media.queuing.conf;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * Created by Chris on 2018/7/2.
 */
@Component
public class ActiveMQQueueConfig {

    /**
     * 创建默认的Queue
     *
     * @return
     */
    @Bean
    public Queue queue() {
        return new ActiveMQQueue("queue.num");
    }


    /**
     * activeMQ message converter json
     *
     * @return
     */
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
}
