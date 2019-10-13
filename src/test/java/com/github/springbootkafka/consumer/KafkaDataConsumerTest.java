package com.github.springbootkafka.consumer;


import com.alibaba.fastjson.JSON;
import com.github.springbootkafka.pojo.KafkaDataDO;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.rule.EmbeddedKafkaRule;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 创建时间为 16:05 2019-08-05
 * 项目名称 spring-boot-kafka
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */

@SpringBootTest
@DirtiesContext
@ActiveProfiles("junit")
@RunWith(SpringRunner.class)
@EmbeddedKafka(controlledShutdown = true, topics = "foo")
public class KafkaDataConsumerTest {

    @Resource
    private EmbeddedKafkaBroker kafkaEmbedded;

    @Value("${spring.embedded.kafka.brokers}")
    private String brokerAddresses;

    private KafkaTemplate<String, String> template;

    @Before
    public void setUp() {
        Map<String, Object> senderProperties = KafkaTestUtils.senderProps(kafkaEmbedded.getBrokersAsString());
        ProducerFactory<String, String> producerFactory = new DefaultKafkaProducerFactory<>(senderProperties);
        template = new KafkaTemplate<>(producerFactory);
    }

    @Test
    public void onMessage() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            template.send("TestTopic1", getKafkaDataDTO("name1"));
            TimeUnit.SECONDS.sleep(1);
        }

    }

    private String getKafkaDataDTO(String name) {
        KafkaDataDO<String> kafkaDataDTO = new KafkaDataDO<>(name, new Date().toString());
        System.out.println("产生的数据:" + JSON.toJSONString(kafkaDataDTO));
        return kafkaDataDTO.toString();
    }


}
