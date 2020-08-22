package com.ihunt.abs

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class AccountServiceApplication

fun main(args: Array<String>) {
    SpringApplication.run(AccountServiceApplication::class.java, *args)
}
