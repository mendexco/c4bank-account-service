package com.mdxco.c4bank.application.web.controllers

import com.mdxco.c4bank.application.web.requests.AccountRequest
import com.mdxco.c4bank.domain.account.entities.Account
import com.mdxco.c4bank.domain.account.facades.AccountFacade
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
    private val accountFacade: AccountFacade
) {
    @PostMapping("/create")
    fun createAccount(@Valid @RequestBody accountData: AccountRequest): ResponseEntity<Account> {
        val createdAccount = accountFacade.createAccount(accountData.toDomain())
        return ResponseEntity(createdAccount, HttpStatus.CREATED)
    }
}