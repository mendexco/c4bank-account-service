package com.mdxco.c4bank.account.application.services.account

import com.mdxco.c4bank.account.domain.account.AccountGateway
import com.mdxco.c4bank.account.domain.account.entities.Account
import com.mdxco.c4bank.account.domain.account.exceptions.AccountNotFoundException
import org.springframework.stereotype.Service

@Service
class GetAccountService(
    private val accountGateway: AccountGateway,
) {
    /**
     * Gets an account by its id.
     * @param id the id of the account to be retrieved
     * @return the account that was retrieved
     */
    fun byId(id: String): Account {
        val account = accountGateway.getById(id) ?: throw AccountNotFoundException()
        return account
    }
}
