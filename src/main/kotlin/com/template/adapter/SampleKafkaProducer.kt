package com.template.adapter

interface SampleKafkaProducer {
    fun sendEvent(eventMessage: String)
}