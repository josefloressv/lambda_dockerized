package com.demo.kotlin_aws_secrets.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class EnvConfig {

    @Value("\${MY_ENV_VAR:default_value}")
    lateinit var myEnvVar: String
}