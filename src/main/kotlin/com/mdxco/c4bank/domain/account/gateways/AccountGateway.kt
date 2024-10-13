package com.mdxco.c4bank.domain.account.gateways

import com.mdxco.c4bank.domain.account.entities.Account

interface AccountGateway {
    fun checkIfAccountIsCreated(taxIdentifier: String): Boolean
    fun createAccount(account: Account): Account
    fun getLatestAccountNumber(): String?
}