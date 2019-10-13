package com.github.springbootkafka.consumer;

import com.alibaba.fastjson.JSON;
import com.github.springbootkafka.pojo.KafkaDataDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 创建时间为 15:10 2019-04-09
 * 项目名称 spring-boot-kafka
 * </p>
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */

@Slf4j
@Service
public class KafkaDataConsumer {

    @KafkaListener(topics = "DataTopic", groupId = "TestGroup", clientIdPrefix = "${spring.cloud.client.ip-address}")
    public void onMessage(List<ConsumerRecord<String, KafkaDataDO<String>>> dataList) {
        // HostInfoEnvironmentPostProcessor
        dataList.stream().map(ConsumerRecord::value).forEach(one -> System.out.println("接收到数据:" + JSON.toJSONString(one)));
    }

}
