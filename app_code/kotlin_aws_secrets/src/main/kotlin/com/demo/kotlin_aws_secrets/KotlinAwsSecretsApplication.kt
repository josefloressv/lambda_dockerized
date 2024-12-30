package com.demo.kotlin_aws_secrets

import com.demo.kotlin_aws_secrets.config.EnvConfig
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean

@SpringBootApplication
class KotlinAwsSecretsApplication {

    @Bean
    fun commandLineRunner(ctx: ApplicationContext, envConfig: EnvConfig) = CommandLineRunner {
        println("MY_ENV_VAR: ${envConfig.myEnvVar}")
    }
}

fun main(args: Array<String>) {
	runApplication<KotlinAwsSecretsApplication>(*args)
}
