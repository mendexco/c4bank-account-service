package com.mdxco.c4bank.account.application.web.controllers

import com.mdxco.c4bank.account.application.services.account.CreateAccountService
import com.mdxco.c4bank.account.application.services.account.DeactivateAccountService
import com.mdxco.c4bank.account.application.services.account.GetAccountService
import com.mdxco.c4bank.account.application.services.account.ReactivateAccountService
import com.mdxco.c4bank.account.application.services.account.UpdateAccountService
import com.mdxco.c4bank.account.application.utils.AppHelpers
import com.mdxco.c4bank.account.application.web.docs.CreateAccountDocs
import com.mdxco.c4bank.account.application.web.docs.DeactivateAccountDocs
import com.mdxco.c4bank.account.application.web.docs.GetAccountDocs
import com.mdxco.c4bank.account.application.web.docs.ReactivateAccountDocs
import com.mdxco.c4bank.account.application.web.docs.UpdateAccountDocs
import com.mdxco.c4bank.account.application.web.requests.CreateAccountRequest
import com.mdxco.c4bank.account.application.web.requests.UpdateAccountRequest
import com.mdxco.c4bank.account.application.web.responses.CreateAccountResponse
import com.mdxco.c4bank.account.application.web.responses.GetAccountResponse
import com.mdxco.c4bank.account.application.web.responses.toCreateAccountResponse
import com.mdxco.c4bank.account.application.web.responses.toGetAccountResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
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
    private val createAccountService: CreateAccountService,
    private val getAccountService: GetAccountService,
    private val updateAccountService: UpdateAccountService,
    private val deactivateAccountService: DeactivateAccountService,
    private val reactivateAccountService: ReactivateAccountService,
) {
    @PostMapping
    @CreateAccountDocs
    fun createAccount(
        @Valid @RequestBody accountData: CreateAccountRequest,
    ): ResponseEntity<CreateAccountResponse> {
        AppHelpers.logger.info("Creating account")

        val createdAccount = createAccountService.execute(accountData.toDomain())
        return ResponseEntity(createdAccount.toCreateAccountResponse(), HttpStatus.CREATED)
    }

    @GetMapping("/{accountId}")
    @GetAccountDocs
    fun getAccount(
        @Valid @PathVariable accountId: String,
    ): ResponseEntity<GetAccountResponse> {
        AppHelpers.logger.info("Getting account by accountId: $accountId")

        val account = getAccountService.byId(accountId)
        return ResponseEntity(account.toGetAccountResponse(), HttpStatus.OK)
    }

    @PutMapping("/{accountId}")
    @UpdateAccountDocs
    fun updateAccount(
        @Valid @PathVariable accountId: String,
        @Valid @RequestBody accountData: UpdateAccountRequest,
    ): ResponseEntity<Void> {
        AppHelpers.logger.info("Updating account by accountId: $accountId")

        updateAccountService.execute(accountId, accountData.toDomain())
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @DeleteMapping("/{accountId}")
    @DeactivateAccountDocs
    fun deleteAccount(
        @Valid @PathVariable accountId: String,
    ): ResponseEntity<Void> {
        AppHelpers.logger.info("Deactivating account by accountId: $accountId")

        deactivateAccountService.execute(accountId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @PutMapping("/{accountId}/reactivate")
    @ReactivateAccountDocs
    fun reactivateAccount(
        @Valid @PathVariable accountId: String,
    ): ResponseEntity<Void> {
        AppHelpers.logger.info("Reactivating account by accountId: $accountId")

        reactivateAccountService.execute(accountId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
