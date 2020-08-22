package com.ihunt.abs.model

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity(name="customer")
class Customer(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @get: NotBlank
        @Column(unique = true)
        val customerID: String = ""
)