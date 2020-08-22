package com.ihunt.abs.model

import javax.persistence.*
import javax.validation.constraints.NotBlank


enum class BalanceType {
    PREPAID, TOPUP
}

@Entity(name = "balance")
class Balance(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @ManyToOne(fetch = FetchType.LAZY, optional = true)
        val customer: Customer?,

        @get: NotBlank
        @Column(unique = true)
        var balanceID: String = "",

        var value: Long = 0L,

        val balanceType: BalanceType = BalanceType.PREPAID
) {
    constructor() : this(0, null, "", 0, BalanceType.PREPAID)
}