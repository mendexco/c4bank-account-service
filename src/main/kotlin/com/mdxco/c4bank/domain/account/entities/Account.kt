package com.mdxco.c4bank.domain.account.entities

import com.mdxco.c4bank.domain.account.entities.enums.AccountStatus
import java.math.BigDecimal

data class Account(
    val address: Address,
    val balance: BigDecimal? = null,
    val id: String? = null,
    val name: String,
    val phone: String? = null,
    val status: AccountStatus? = null,
    val taxIdentifier: String
)