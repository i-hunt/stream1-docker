package com.ihunt.abs.controller

import com.ihunt.abs.dto.AddBalanceDTO
import com.ihunt.abs.dto.CreateBalanceDTO
import com.ihunt.abs.model.Balance
import com.ihunt.abs.model.BalanceType
import com.ihunt.abs.repository.BalanceRepository
import com.ihunt.abs.repository.CustomerRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class BalanceController(private val balanceRepository: BalanceRepository,
                        private val customerRepository: CustomerRepository) {
    @GetMapping("/balances")
    fun getAllBalances(): List<Balance> =
            balanceRepository.findAll()

    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping("/balances/add")
    fun createNewBalance(@Valid @RequestBody createBalanceDTO: CreateBalanceDTO): ResponseEntity<Balance> {
        var customer = customerRepository.findByCustomerID(createBalanceDTO.customerID)
        if (customer == null) {
            logger.error("Found no customer ${createBalanceDTO.customerID}")
            return ResponseEntity.notFound().build();
        }

        val balance = Balance(customer = customer, balanceType = BalanceType.valueOf(createBalanceDTO.balanceType),
        value = createBalanceDTO.value, balanceID = createBalanceDTO.balanceID)
        balanceRepository.save(balance)

        return ResponseEntity.ok(balance)
    }


    @PostMapping("/balances/{balanceID}/topup")
    fun topupBalance(@PathVariable(value = "balanceID") balanceID: String,
                     @Valid @RequestBody increment: AddBalanceDTO): ResponseEntity<Balance> {
        var balance = balanceRepository.findByBalanceID(balanceID)
        if (balance == null) {
            logger.error("Found no balance $balanceID")
            return ResponseEntity.notFound().build();
        }
        balance.value = balance.value + increment.value
        balanceRepository.save(balance)
        return ResponseEntity.ok(balance)
    }


    @GetMapping("/balances/{balanceID}")
    fun getBalanceByBalanceID(@PathVariable(value = "balanceID") balanceID: String): ResponseEntity<Balance> {
        var balance = balanceRepository.findByBalanceID(balanceID)
        if (balance == null) {
            logger.error("Found no balance $balanceID")
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(balance)
    }

    @DeleteMapping("/balances/{balanceID}")
    fun deleteBalanceByBalanceID(@PathVariable(value = "balanceID") balanceID: String): ResponseEntity<Void> {
        var balance = balanceRepository.findByBalanceID(balanceID)
        if (balance == null) {
            logger.error("Found no balance $balanceID")
            return ResponseEntity.notFound().build();
        }

        balanceRepository.delete(balance)
        return ResponseEntity<Void>(HttpStatus.OK)
    }
}