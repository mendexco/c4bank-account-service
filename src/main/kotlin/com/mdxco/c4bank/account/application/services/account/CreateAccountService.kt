package com.mdxco.c4bank.account.application.services.account

import com.mdxco.c4bank.account.application.services.address.AddAddressService
import com.mdxco.c4bank.account.domain.account.AccountGateway
import com.mdxco.c4bank.account.domain.account.entities.Account
import com.mdxco.c4bank.account.domain.account.entities.AccountToBeCreated
import com.mdxco.c4bank.account.domain.utils.LockingHelpers
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CreateAccountService(
    private val accountGateway: AccountGateway,
    private val addAddressService: AddAddressService,
) {
    /**
     * Creates an account in the database.
     * @param accountToBeCreated the account to be created
     * @return the account that was created
     */
    @Transactional
    fun execute(accountToBeCreated: AccountToBeCreated): Account {
        return LockingHelpers.withLock {
            val address = addAddressService.execute(accountToBeCreated.address)

            return@withLock Account.create(
                accountGateway,
                accountToBeCreated = accountToBeCreated.updateAddress(address),
            )
        }
    }
}
