package com.template.adapter

import com.template.sample.SampleRequestDto

interface SampleKafkaProducer {
    fun sendEvent(dto: SampleRequestDto)
}