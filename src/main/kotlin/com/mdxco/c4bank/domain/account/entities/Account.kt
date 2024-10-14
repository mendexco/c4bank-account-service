package com.mdxco.c4bank.domain.account.entities

import com.mdxco.c4bank.domain.account.entities.enums.AccountStatus
import java.math.BigDecimal

data class Account(
    val accountNumber: String? = null,
    val address: Address,
    val balance: BigDecimal? = null,
    val id: String? = null,
    val name: String,
    val phone: String? = null,
    val status: AccountStatus? = null,
    val taxIdentifier: String
)

data class AccountUpdate(
    val address: Address? = null,
    val id: String,
    val phone: String? = null
)