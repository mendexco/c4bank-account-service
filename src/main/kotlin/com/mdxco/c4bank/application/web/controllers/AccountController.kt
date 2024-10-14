package com.mdxco.c4bank.application.web.controllers

import com.mdxco.c4bank.application.web.docs.CreateAccountDocs
import com.mdxco.c4bank.application.web.docs.UpdateAccountDocs
import com.mdxco.c4bank.application.web.requests.CreateAccountRequest
import com.mdxco.c4bank.application.web.requests.UpdateAccountRequest
import com.mdxco.c4bank.application.web.responses.CreateAccountResponse
import com.mdxco.c4bank.application.web.responses.toCreateAccountResponse
import com.mdxco.c4bank.domain.account.facades.AccountFacade
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/accounts")
@Tag(name = "Account", description = "Account management operations")
class AccountController(
    private val accountFacade: AccountFacade
) {
    @PostMapping
    @CreateAccountDocs
    fun createAccount(@Valid @RequestBody accountData: CreateAccountRequest): ResponseEntity<CreateAccountResponse> {
        val createdAccount = accountFacade.createAccount(accountData.toDomain())
        return ResponseEntity(createdAccount.toCreateAccountResponse(), HttpStatus.CREATED)
    }

    @PutMapping
    @UpdateAccountDocs
    fun updateAccount(@Valid @RequestBody accountData: UpdateAccountRequest): ResponseEntity<Void> {
        accountFacade.updateAccount(accountData.toDomain())
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}