package com.mdxco.c4bank.account.application.services.account

import com.mdxco.c4bank.account.application.services.address.UpdateAddressService
import com.mdxco.c4bank.account.domain.account.AccountGateway
import com.mdxco.c4bank.account.domain.account.entities.Account
import com.mdxco.c4bank.account.domain.account.entities.AccountUpdates
import com.mdxco.c4bank.account.domain.account.exceptions.InsufficientDataToUpdateException
import com.mdxco.c4bank.account.domain.utils.LockingHelpers
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UpdateAccountService(
    private val accountGateway: AccountGateway,
    private val getAccountService: GetAccountService,
    private val updateAddressService: UpdateAddressService,
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
        if (Account.haveInsufficientDataToUpdate(accountUpdates)) {
            throw InsufficientDataToUpdateException()
        }

        val accountFoundById = getAccountService.byId(accountId)
        val addressUpdated = updateAddressService.execute(accountFoundById.address, accountUpdates.address)

        return LockingHelpers.withLock {
            return@withLock accountFoundById.update(
                accountGateway,
                address = addressUpdated.first,
                phone = accountUpdates.phone,
            )
        }
    }
}
