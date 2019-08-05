package com.github.springbootkafka.pojo;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 创建时间为 15:09 2019-04-09
 * 项目名称 spring-boot-kafka
 * </p>
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaDataDO<T> {
    private String name;

    private T t;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
