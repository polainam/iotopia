package com.iotopia.sensor.config

import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class KafkaConfig {

    @Value("\${spring.kafka.bootstrap-servers}")
    private lateinit var bootstrapServers: String

    @Value("\${spring.kafka.producer.key-serializer}")
    private lateinit var keySerializer: String

    @Value("\${spring.kafka.producer.value-serializer}")
    private lateinit var valueSerializer: String

    @Value("\${spring.kafka.producer.acks}")
    private lateinit var acks: String

    @Value("\${spring.kafka.producer.properties.delivery.timeout.ms}")
    private lateinit var deliveryTimeout: String

    @Value("\${spring.kafka.producer.properties.linger.ms}")
    private lateinit var linger: String

    @Value("\${spring.kafka.producer.properties.request.timeout.ms}")
    private lateinit var requestTimeout: String

    fun configs(): Map<String, String> {
        val configs = mutableMapOf<String, String>()
        return configs.apply {
            put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers)
            put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer) // а как в проекте настроено
            put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer)
            put(ProducerConfig.ACKS_CONFIG, acks)
            put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, deliveryTimeout)
            put(ProducerConfig.LINGER_MS_CONFIG, linger)
            put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, requestTimeout)
        }
    }

    @Bean
    fun createTopic(): NewTopic =
        TopicBuilder.name("iot.sensors.topic")
            .partitions(3)
            .replicas(3)
            .config("min.insync.replicas", "2")
            .build()

    @Bean
    fun producerFactory(): ProducerFactory<String, String> =
        DefaultKafkaProducerFactory(configs())

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, String> =
        KafkaTemplate(producerFactory())
}
