package com.demo.kotlin_aws_secrets.config

import com.demo.kotlin_aws_secrets.service.AwsSecretsManagerService
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct
import org.json.JSONObject

@Configuration
class EnvConfig(private val awsSecretsManagerService: AwsSecretsManagerService) {

    @Value("\${MY_ENV_VAR:default_value}")
    lateinit var myEnvVar: String

    lateinit var sec1: String
    lateinit var sec2: String
    lateinit var param1: String

    @PostConstruct
    fun init() {
        val secretString = awsSecretsManagerService.getSecret("/dev/lambdapoc")
        if (secretString != null) {
            val jsonObject = JSONObject(secretString)
            sec1 = jsonObject.getString("sec1")
            sec2 = jsonObject.getString("sec2")
        } else {
            sec1 = "default_sec1"
            sec2 = "default_sec2"
        }

        param1 = awsSecretsManagerService.getParameter("/dev/lambdapoc/param1") ?: "default_param1"
    }
}