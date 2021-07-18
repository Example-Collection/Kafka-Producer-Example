package com.template.sample

import com.template.common.dto.SimpleResponseDto
import org.springframework.stereotype.Service

@Service
class SampleService {

    fun sendMessage(): SimpleResponseDto {
        // TODO: Send Message
        return SimpleResponseDto("Message successfully sent!")
    }
}