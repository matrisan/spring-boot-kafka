package com.github.springbootkafka.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.springbootkafka.pojo.KafkaDataDO;
import com.github.springbootkafka.pojo.KafkaDateDO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

/**
 * <p>
 * 创建时间为 15:11 2019-04-09
 * 项目名称 spring-boot-kafka
 * </p>
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */

@Slf4j
public class KafkaDateDeserializer implements Deserializer<KafkaDateDO<String>> {

    private String encoding = "UTF8";

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        String propertyName = isKey ? "key.deserializer.encoding" : "value.deserializer.encoding";
        Object encodingValue = configs.get(propertyName);
        if (encodingValue == null) {
            encodingValue = configs.get("deserializer.encoding");
        }
        if (encodingValue instanceof String) {
            encoding = (String) encodingValue;
        }
    }

    @SneakyThrows(IOException.class)
    @Override
    public KafkaDateDO<String> deserialize(String topic, byte[] data) {
        return (null == data) ? null : JSON.parseObject(new String(data, encoding), new TypeReference<KafkaDateDO<String>>() {
        });
    }

    @Override
    public void close() {
        // nothing to do
    }
}
