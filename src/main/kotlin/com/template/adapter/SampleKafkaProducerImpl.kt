package com.template.adapter

import com.fasterxml.jackson.databind.ObjectMapper
import com.template.config.KafkaProperties
import com.template.domain.event.SampleEvent
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

@Service
class SampleKafkaProducerImpl(private val kafkaProperties: KafkaProperties): SampleKafkaProducer {

    private val logger = LoggerFactory.getLogger(SampleKafkaProducerImpl::class.java)

    companion object {
        private const val TOPIC_MESSAGE = "TOPIC_MESSAGE"
    }

    private val objectMapper: ObjectMapper = ObjectMapper()
    private lateinit var producer: KafkaProducer<String, String>

    @PostConstruct
    fun initialize() {
        logger.info("Kafka Producer initializing..")
        this.producer = KafkaProducer<String, String>(kafkaProperties.getProducerProps())
        Runtime.getRuntime().addShutdownHook(Thread(this::shutdown))
    }

    @PreDestroy
    fun shutdown() {
        logger.info("Shutting down Kafka Producer..")
        producer.close()
    }

    override fun sendEvent(eventMessage: String) {
        val sampleEvent = SampleEvent("Message One", "Message Two")
        val message = objectMapper.writeValueAsString(sampleEvent)
        producer.send(ProducerRecord(TOPIC_MESSAGE, message)).get()
    }
}