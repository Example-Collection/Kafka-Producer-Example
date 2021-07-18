package com.template.config

import org.springframework.context.annotation.Configuration

@Configuration
class KafkaProperties {

    private val bootStrapServer = "localhost:9092"
    private val producer = mutableMapOf<String, String>()

    fun getProducerProps(): Map<String, Any> {
        val properties = this.producer
        properties.putIfAbsent("bootstrap.servers", this.bootStrapServer)
        properties.putIfAbsent("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
        properties.putIfAbsent("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
        return properties
    }
}