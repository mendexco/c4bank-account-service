package com.mdxco.c4bank.account.application.services.account

import com.mdxco.c4bank.account.domain.account.AccountGateway
import com.mdxco.c4bank.account.domain.account.entities.Account
import com.mdxco.c4bank.account.domain.account.entities.AccountUpdates
import com.mdxco.c4bank.account.domain.address.AddressGateway
import com.mdxco.c4bank.account.domain.address.messaging.AddressProducer
import com.mdxco.c4bank.account.domain.utils.LockingHelpers
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UpdateAccountService(
    private val accountGateway: AccountGateway,
    private val addressGateway: AddressGateway,
    private val addressProducer: AddressProducer,
    private val getAccountService: GetAccountService,
) {
    /**
     * Updates an account with the given account updates.
     * @param accountId the account id to update
     * @param accountUpdates the account updates to apply
     * @return the updated account
     */
    @Transactional
    fun execute(
        accountId: String,
        accountUpdates: AccountUpdates,
    ): Account {
        return LockingHelpers.withLock {
            val accountFoundById = getAccountService.byId(accountId)
            val addressUpdated = accountFoundById.address.update(
                addressGateway,
                addressProducer,
                accountUpdates.address
            ).first

            return@withLock accountFoundById.update(
                accountGateway,
                address = addressUpdated,
                phone = accountUpdates.phone,
            )
        }
    }
}
