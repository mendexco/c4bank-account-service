package com.mdxco.c4bank.application.controllers

import com.mdxco.c4bank.application.requests.AccountDataRequest
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/accounts")
@Tag(name = "Account", description = "Account management operations")
class AccountController {

    @PostMapping("/create")
    fun createAccount(@Valid @RequestBody accountData: AccountDataRequest): ResponseEntity<AccountDataRequest> {
        return ResponseEntity.ok(accountData)
    }
}