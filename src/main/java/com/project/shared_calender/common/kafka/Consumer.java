package com.project.shared_calender.common.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class Consumer {

    @KafkaListener(topics = "chat", groupId = "chat-center")
    public void receiveMessage(Object object) {
    }
}
