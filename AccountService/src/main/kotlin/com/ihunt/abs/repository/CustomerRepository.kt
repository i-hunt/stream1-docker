package com.ihunt.abs.repository

import com.ihunt.abs.model.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : JpaRepository<Customer, Long> {
    fun findByCustomerID(customerID: String): Customer?
}