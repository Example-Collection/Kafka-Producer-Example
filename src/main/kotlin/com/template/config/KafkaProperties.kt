package com.template.config

class KafkaProperties {

    private val bootStrapServer = "localhost:8082"
    private val producer = mutableMapOf<String, String>()

    public fun getProducerProps(): Map<String, Any> {
        val properties = this.producer
        properties.putIfAbsent("bootstrap.server", this.bootStrapServer)
        return properties
    }
}