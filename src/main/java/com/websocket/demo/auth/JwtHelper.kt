package com.websocket.demo.auth

import com.websocket.demo.config.UserPrincipal
import io.jsonwebtoken.*
import org.slf4j.LoggerFactory
import java.time.ZonedDateTime
import java.util.*


object JwtHelper {
    private val logger = LoggerFactory.getLogger(JwtHelper::class.java)
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

    fun toUser(accessToken: String?): UserPrincipal? {
        return try {
            return Jwts.parser().parseClaimsJwt(accessToken).body.let {
                UserPrincipal(it["id"] as Long, it["userName"] as String, it["roles"] as List<String>)
            }
        } catch (e: ExpiredJwtException) {
            logger.error("expired jwt", e)
            null
        } catch (e: UnsupportedJwtException) {
            logger.error("unsupported jwt", e)
            null
        } catch (e: MalformedJwtException) {
            logger.error("malformed jwt jwt", e)
            null
        } catch (e: SignatureException) {
            logger.error("invalid signature jwt", e)
            null
        } catch (e: IllegalArgumentException) {
            logger.error("illegal argument", e)
            null
        }
    }
}