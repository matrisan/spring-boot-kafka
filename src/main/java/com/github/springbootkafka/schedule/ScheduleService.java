package com.github.springbootkafka.schedule;

import com.alibaba.fastjson.JSON;
import com.github.springbootkafka.pojo.KafkaDataDO;
import com.github.springbootkafka.producer.KafkaProducer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

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
@EnableScheduling
public class ScheduleService {

    @Resource
    private KafkaProducer producer;

    @Scheduled(fixedRate = 1000)
    public void task() {
        producer.onSend("TestTopic", (getKafkaDataDTO("name1")));
        producer.onSend("TestTopic", (getKafkaDataDTO("name1")));
    }

    private KafkaDataDO<String> getKafkaDataDTO(String name) {
        KafkaDataDO<String> kafkaDataDTO = new KafkaDataDO<>(name, new Date().toString());
        System.out.println("产生的数据:" + JSON.toJSONString(kafkaDataDTO));
        return kafkaDataDTO;
    }

}