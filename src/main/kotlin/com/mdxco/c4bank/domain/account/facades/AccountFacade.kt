package com.mdxco.c4bank.domain.account.facades

import com.mdxco.c4bank.domain.account.entities.Account
import com.mdxco.c4bank.domain.account.services.AccountService
import com.mdxco.c4bank.domain.account.services.AddressService
import org.springframework.stereotype.Service

@Service
class AccountFacade(
    private val accountService: AccountService,
    private val addressService: AddressService
) {
    fun createAccount(account: Account): Account {
        val address = addressService.addAddress(account.address)
        return accountService.createAccount(account.copy(address = address))
    }
}