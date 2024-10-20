package com.mdxco.c4bank.account.application.web.responses

import com.mdxco.c4bank.account.domain.account.entities.Account
import io.swagger.v3.oas.annotations.media.Schema

data class CreateAccountResponse(
    @Schema(description = "Unique recently generated account number", example = "123456-7")
    val accountNumber: String,
)

fun Account.toCreateAccountResponse() = CreateAccountResponse(
    accountNumber = accountNumber!!
)
