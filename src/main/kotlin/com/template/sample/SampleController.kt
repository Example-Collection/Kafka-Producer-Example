package com.template.sample

import com.template.common.dto.SimpleResponseDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleController(private val sampleService: SampleService) {

    @PostMapping("/v1/sample/kafka")
    fun sendEvent(@RequestBody dto: SampleRequestDto): SimpleResponseDto {
        return sampleService.sendMessage(dto)
    }
}