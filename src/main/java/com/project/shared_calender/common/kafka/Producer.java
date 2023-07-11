package com.project.shared_calender.common.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class Producer {
    private static final String TOPIC = "chat";
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(Object object) {
        this.kafkaTemplate.send(TOPIC, object);
    }
}
