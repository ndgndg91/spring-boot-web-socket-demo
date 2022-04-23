package com.websocket.demo.config

import java.security.Principal

class UserPrincipal(val id: Long, val userName: String, val roles: List<String>): Principal {
    override fun getName(): String {
        return this.userName
    }
}