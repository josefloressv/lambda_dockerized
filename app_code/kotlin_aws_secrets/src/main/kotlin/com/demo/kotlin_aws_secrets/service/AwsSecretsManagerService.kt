package com.demo.kotlin_aws_secrets.service

import org.springframework.stereotype.Service
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest

@Service
class AwsSecretsManagerService {

    private val client = SecretsManagerClient.builder()
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
}