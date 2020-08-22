package com.ihunt.abs.repository

import com.ihunt.abs.model.Balance
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BalanceRepository : JpaRepository<Balance, Long> {
    fun findByBalanceID(bid: String): Balance?
}