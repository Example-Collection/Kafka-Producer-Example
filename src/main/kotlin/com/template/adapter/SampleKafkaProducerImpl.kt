package com.template.adapter

import com.fasterxml.jackson.databind.ObjectMapper
import com.template.config.KafkaProperties
import com.template.domain.event.SampleEvent
import com.template.sample.SampleRequestDto
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

@Service
class SampleKafkaProducerImpl(kafkaProperties: KafkaProperties): SampleKafkaProducer {

    private val logger = LoggerFactory.getLogger(SampleKafkaProducerImpl::class.java)

    companion object {
        private const val TOPIC_MESSAGE = "TOPIC_MESSAGE"
    }

    private val objectMapper: ObjectMapper = ObjectMapper()
    private var producer = KafkaProducer<String, String>(kafkaProperties.getProducerProps())
    @PostConstruct
    fun initialize() {
        logger.info("Kafka Producer initializing..")
        Runtime.getRuntime().addShutdownHook(Thread(this::shutdown))
    }

    @PreDestroy
    fun shutdown() {
        logger.info("Shutting down Kafka Producer..")
        producer.close()
    }

    override fun sendEvent(dto: SampleRequestDto) {
        val sampleEvent = SampleEvent(dto.messageOne, dto.messageTwo)
        val message = objectMapper.writeValueAsString(sampleEvent)
        producer.send(ProducerRecord(TOPIC_MESSAGE, message)).get()
    }
}