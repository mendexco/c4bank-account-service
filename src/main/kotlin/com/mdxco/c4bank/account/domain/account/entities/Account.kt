package com.mdxco.c4bank.account.domain.account.entities

import com.mdxco.c4bank.account.domain.account.entities.enums.AccountStatus
import java.math.BigDecimal

data class Account(
    val accountNumber: String? = null,
    val address: Address,
    val balance: BigDecimal? = null,
    val id: String? = null,
    val name: String,
    val phone: String? = null,
    val status: AccountStatus? = null,
    val taxIdentifier: String,
    val version: Long? = null,
)

data class AccountUpdate(
    val address: Address? = null,
    val phone: String? = null,
)
