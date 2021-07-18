package com.template.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "kafka")
class KafkaProperties {

    private val bootStrapServer = "localhost:9092"
    private val producer = mutableMapOf<String, String>()

    fun getProducerProps(): Map<String, Any> {
        val properties = this.producer
        properties.putIfAbsent("bootstrap.servers", this.bootStrapServer)
        return properties
    }
}