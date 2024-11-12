package com.mdxco.c4bank.account.application.services.account

import com.mdxco.c4bank.account.domain.account.AccountGateway
import com.mdxco.c4bank.account.domain.account.entities.Account
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReactivateAccountService(
    private val accountGateway: AccountGateway,
    private val getAccountService: GetAccountService,
) {
    /**
     * Reactivates an account in the database.
     * @param accountId the id of the account to be reactivated
     * @return the account that was reactivated
     */
    @Transactional
    fun execute(accountId: String): Account {
        val account = getAccountService.byId(accountId)
        return account.reactivate(accountGateway)
    }
}
