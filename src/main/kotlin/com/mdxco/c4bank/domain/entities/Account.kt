package com.mdxco.c4bank.domain.entities

import com.mdxco.c4bank.domain.entities.enums.AccountStatus
import java.math.BigDecimal

data class Account(
    val address: AccountAddress,
    val balance: BigDecimal,
    val id: String,
    val name: String,
    val phone: String,
    val status: AccountStatus,
    val taxIdentifier: String
)