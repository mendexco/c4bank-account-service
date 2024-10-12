package com.mdxco.c4bank.application.web.controllers

import com.mdxco.c4bank.application.web.requests.CreateAccountRequest
import com.mdxco.c4bank.domain.account.entities.Account
import com.mdxco.c4bank.domain.account.services.AccountService
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/accounts")
@Tag(name = "Account", description = "Account management operations")
class AccountController(
    private val accountService: AccountService
) {

    @PostMapping("/create")
    fun createAccount(@Valid @RequestBody accountData: CreateAccountRequest): ResponseEntity<Account> {
        val createdAccount = accountService.createAccount(accountData.toDomain())
        return ResponseEntity(createdAccount, HttpStatus.CREATED)
    }
}