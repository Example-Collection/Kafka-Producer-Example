package com.template.sample

import com.template.adapter.SampleKafkaProducer
import com.template.common.dto.SimpleResponseDto
import org.springframework.stereotype.Service

@Service
class SampleService(private val kafkaProducer: SampleKafkaProducer) {

    fun sendMessage(dto: SampleRequestDto): SimpleResponseDto {
        kafkaProducer.sendEvent(dto)
        return SimpleResponseDto("Message successfully sent!")
    }
}