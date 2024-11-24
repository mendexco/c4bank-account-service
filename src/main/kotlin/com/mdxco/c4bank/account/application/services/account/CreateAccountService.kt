package com.mdxco.c4bank.account.application.services.account

import com.mdxco.c4bank.account.domain.account.AccountGateway
import com.mdxco.c4bank.account.domain.account.entities.Account
import com.mdxco.c4bank.account.domain.account.entities.AccountToBeCreated
import com.mdxco.c4bank.account.domain.address.AddressGateway
import com.mdxco.c4bank.account.domain.utils.LockingHelpers
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CreateAccountService(
    private val accountGateway: AccountGateway,
    private val addressGateway: AddressGateway
) {
    /**
     * Creates an account in the database.
     * @param accountToBeCreated the account to be created
     * @return the account that was created
     */
    @Transactional
    fun execute(accountToBeCreated: AccountToBeCreated): Account {
        return LockingHelpers.withLock {
            Account.create(
                accountGateway,
                addressGateway,
                accountToBeCreated = accountToBeCreated,
            )
        }
    }
}
