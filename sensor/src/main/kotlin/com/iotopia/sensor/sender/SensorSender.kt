package com.iotopia.sensor.sender

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class SensorSender(
    private val kafkaTemplate: KafkaTemplate<String, String>, // поч не пишем аннотаций
    private final val topic: String = "iot.sensors.topic"
) {
    fun send(key: String, value: String) {
        kafkaTemplate.send(topic, key, value)
    }
}
