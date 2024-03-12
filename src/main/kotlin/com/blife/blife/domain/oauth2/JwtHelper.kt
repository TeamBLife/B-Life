package com.blife.blife.domain.oauth2

import org.springframework.stereotype.Component

@Component
class JwtHelper {
    fun generateAccessToken(id: Long?): String {
        return "SampleAccessToken $id"
    }
}