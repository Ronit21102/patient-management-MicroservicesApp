package com.pm.analytics_service.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@Service
public class kafkaConsumer {
    private static final Logger log = LoggerFactory.getLogger(kafkaConsumer.class);

    @KafkaListener(topics = "patient-events", groupId = "analytics-service")
    public void consumeEvent(byte[] event) {
        try {

            PatientEvent patientEvent = PatientEvent.parseFrom(event);
              // we can perform any buisness logic related to event
            log.info("Received event: PatientId: {}, Name: {}, Email: {}, EventType: {}", patientEvent.getPatientId(), patientEvent.getName(), patientEvent.getEmail(), patientEvent.getEventType());
        } catch (InvalidProtocolBufferException e) {
            log.error("Error while consuming event: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
