package com.ihunt.abs.controller

import com.ihunt.abs.model.Customer
import com.ihunt.abs.repository.BalanceRepository
import com.ihunt.abs.repository.CustomerRepository
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class CustomerController(private val customerRepository: CustomerRepository,
                         private val balanceRepository: BalanceRepository) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping("/customers")
    fun getAllCustomers(): List<Customer> =
            customerRepository.findAll()


    @PostMapping("/customers")
    fun createNewCustomer(@Valid @RequestBody customer: Customer): ResponseEntity<Customer> {
        var c = customerRepository.findByCustomerID(customer.customerID)
        if (c != null) {
            logger.error("Found dup customer ${customer.customerID}")
            return ResponseEntity.badRequest().build();
        }
        customerRepository.save(customer)
        return ResponseEntity.ok(customer)
    }


    @GetMapping("/customers/{customerID}")
    fun getCustomerByCustomerPK(@PathVariable(value = "customerID") customerID: String): ResponseEntity<Customer> {
        var customer = customerRepository.findByCustomerID(customerID)
        if (customer == null) {
            logger.error("Found no customer $customerID")
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer)
    }

    @DeleteMapping("/customers/{customerID}")
    fun deleteCustomerByCustomerID(@PathVariable(value = "customerID") customerID: String): ResponseEntity<Void> {
        var customer = customerRepository.findByCustomerID(customerID)
        if (customer == null) {
            logger.error("Found no customer $customerID")
            return ResponseEntity.notFound().build();
        }

        customerRepository.delete(customer)
        logger.info("Deleted customer $customerID")
        return ResponseEntity<Void>(HttpStatus.OK)
    }
}