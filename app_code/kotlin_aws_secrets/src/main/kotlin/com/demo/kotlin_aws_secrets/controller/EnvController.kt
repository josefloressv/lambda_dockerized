package com.demo.kotlin_aws_secrets.controller

import com.demo.kotlin_aws_secrets.config.EnvConfig
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class EnvController(private val envConfig: EnvConfig) {

    @GetMapping("/")
    fun index(model: Model): String {
        model.addAttribute("myEnvVar", envConfig.myEnvVar)
        return "index"
    }
}