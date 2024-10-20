package com.mdxco.c4bank.account.domain.account.gateways

import com.mdxco.c4bank.account.domain.account.entities.Account

interface AccountGateway {
    fun checkIfAccountIsCreated(taxIdentifier: String): Boolean
    fun checkIfAddressIsInUse(addressId: String): Boolean
    fun saveAccount(account: Account): Account
    fun getLatestAccountNumber(): String?
    fun getById(id: String): Account?
}