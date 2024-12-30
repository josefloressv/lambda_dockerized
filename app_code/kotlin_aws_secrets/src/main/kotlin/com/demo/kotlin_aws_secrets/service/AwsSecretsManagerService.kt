package com.demo.kotlin_aws_secrets.service

import org.springframework.stereotype.Service
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest
import software.amazon.awssdk.services.ssm.SsmClient
import software.amazon.awssdk.services.ssm.model.GetParameterRequest

@Service
class AwsSecretsManagerService {

    private val client = SecretsManagerClient.builder()
        .region(Region.US_EAST_1)
        .credentialsProvider(DefaultCredentialsProvider.create())
        .build()

    private val ssmClient = SsmClient.builder()
        .region(Region.US_EAST_1)
        .credentialsProvider(DefaultCredentialsProvider.create())
        .build()

    fun getSecret(secretName: String): String? {
        val getSecretValueRequest = GetSecretValueRequest.builder()
            .secretId(secretName)
            .build()

        val getSecretValueResponse = client.getSecretValue(getSecretValueRequest)
        return getSecretValueResponse.secretString()
    }

    fun getParameter(parameterName: String): String? {
        val getParameterRequest = GetParameterRequest.builder()
            .name(parameterName)
            .withDecryption(true)
            .build()

        val getParameterResponse = ssmClient.getParameter(getParameterRequest)
        return getParameterResponse.parameter().value()
    }
}