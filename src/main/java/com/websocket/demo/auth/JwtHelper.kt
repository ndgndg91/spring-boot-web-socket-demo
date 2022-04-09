package com.websocket.demo.auth

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.time.ZonedDateTime
import java.util.*


object JwtHelper {
    private const val KEY = "my-new-secret-key"
    private const val EXPIRATION_MINUTES: Long = 259200 // 180일

    private var HEADERS = mapOf("typ" to "JWT", "alg" to SignatureAlgorithm.HS256.name)

    fun createJwt(
        id: Long,
        userName: String,
    ): String {
        val claims: MutableMap<String, Any> = HashMap()
        claims["id"] = id
        claims["userName"] = userName
        claims["roles"] = arrayOf("USER")
        return Jwts.builder()
            .setHeader(HEADERS)
            .setClaims(claims)
            .setIssuedAt(Date())
            .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(EXPIRATION_MINUTES).toInstant()))
            .signWith(SignatureAlgorithm.HS256, KEY.toByteArray())
            .compact()
    }
}