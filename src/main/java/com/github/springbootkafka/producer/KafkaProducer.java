package com.github.springbootkafka.producer;

import com.github.springbootkafka.pojo.KafkaDataDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;

/**
 * <p>
 * 创建时间为 15:12 2019-04-09
 * 项目名称 spring-boot-kafka
 * </p>
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */
@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, KafkaDataDO<String>> kafkaTemplate;

    public void onSend(String topic, KafkaDataDO<String> data) {
        kafkaTemplate.send(topic, data);
    }

}
