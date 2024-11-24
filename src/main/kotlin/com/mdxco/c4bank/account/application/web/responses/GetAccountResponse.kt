package com.mdxco.c4bank.account.application.web.responses

import com.mdxco.c4bank.account.domain.account.entities.Account
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal

data class GetAccountResponse(
    @Schema(description = "Unique recently generated account number", example = "123456-7") val accountNumber: String,
    @Schema(description = "Monetary balance", example = "1000.00") val balance: BigDecimal,
    @Schema(description = "Owner name", example = "John Doe") val name: String,
    @Schema(description = "Optional phone", example = "11987654321") val phone: String?,
    @Schema(description = "Account current status", example = "ACTIVE") val status: String,
    @Schema(description = "Owner tax identifier", example = "12345678901") val taxIdentifier: String,
)

fun Account.toGetAccountResponse() = GetAccountResponse(
    accountNumber = accountNumber.value,
    balance = balance,
    name = name.value,
    phone = phone?.value,
    status = status!!.name,
    taxIdentifier = taxIdentifier.value,
)
