package com.websocket.demo.config

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.boot.web.servlet.filter.OrderedFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Configuration
open class CorsConfig {

    @Bean
    open fun corsFilter(): FilterRegistrationBean<CorsFilter> {
        val urlBasedCorsConfigurationSource = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()
        config.allowedOrigins = mutableListOf("http://localhost:4200")
        config.allowedHeaders = mutableListOf("*")
        config.allowedMethods = mutableListOf("*")
        config.allowCredentials = true
        config.maxAge = 3600
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", config)
        val corsFilter = CorsFilter(urlBasedCorsConfigurationSource)
        val filterRegistrationBean = FilterRegistrationBean(corsFilter)
        filterRegistrationBean.order = OrderedFilter.HIGHEST_PRECEDENCE
        return filterRegistrationBean
    }
}