package com.mdxco.c4bank.application.web.controllers

import com.mdxco.c4bank.application.utils.AppHelpers
import com.mdxco.c4bank.application.web.docs.CreateAccountDocs
import com.mdxco.c4bank.application.web.docs.DeactivateAccountDocs
import com.mdxco.c4bank.application.web.docs.ReactivateAccountDocs
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
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
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
        AppHelpers.logger.info("Creating account")

        val createdAccount = accountFacade.createAccount(accountData.toDomain())
        return ResponseEntity(createdAccount.toCreateAccountResponse(), HttpStatus.CREATED)
    }

    @PutMapping("/{accountId}")
    @UpdateAccountDocs
    fun updateAccount(
        @Valid @PathVariable accountId: String,
        @Valid @RequestBody accountData: UpdateAccountRequest
    ): ResponseEntity<Void> {
        AppHelpers.logger.info("Updating account by accountId: $accountId")

        accountFacade.updateAccount(accountId, accountData.toDomain())
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @DeleteMapping("/{accountId}")
    @DeactivateAccountDocs
    fun deleteAccount(@Valid @PathVariable accountId: String): ResponseEntity<Void> {
        AppHelpers.logger.info("Deactivating account by accountId: $accountId")

        accountFacade.deactivateAccount(accountId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @PutMapping("/{accountId}/reactivate")
    @ReactivateAccountDocs
    fun reactivateAccount(@Valid @PathVariable accountId: String): ResponseEntity<Void> {
        AppHelpers.logger.info("Reactivating account by accountId: $accountId")
        
        accountFacade.reactivateAccount(accountId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}