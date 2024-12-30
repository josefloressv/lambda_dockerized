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
        model.addAttribute("sec1", envConfig.sec1)
        model.addAttribute("sec2", envConfig.sec2)
        model.addAttribute("param1", envConfig.param1)
        return "index"
    }
}