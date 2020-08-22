package com.ihunt.abs.dto

import com.ihunt.abs.model.BalanceType

data class AddBalanceDTO(
    var value:Long = 0L
)

data class CreateBalanceDTO(
        var value:Long = 0L,
        var balanceID: String,
        var customerID: String,
        var balanceType: String
)