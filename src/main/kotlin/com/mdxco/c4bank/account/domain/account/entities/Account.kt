package com.mdxco.c4bank.account.domain.account.entities

import com.mdxco.c4bank.account.commons.helpers.AccountHelpers
import com.mdxco.c4bank.account.domain.account.AccountGateway
import com.mdxco.c4bank.account.domain.account.entities.values.Name
import com.mdxco.c4bank.account.domain.account.entities.values.Phone
import com.mdxco.c4bank.account.domain.account.entities.values.TaxIdentifier
import com.mdxco.c4bank.account.domain.account.exceptions.AccountAlreadyExistsException
import com.mdxco.c4bank.account.domain.account.utils.AccountStatus
import com.mdxco.c4bank.account.domain.address.entities.Address
import java.math.BigDecimal

/**
 * Represents an account.
 * @property id the account id
 * @property version the account version for locking purposes
 * @property accountNumber auto generated account number
 * @property address the account address
 * @property balance the account balance
 * @property name the account name
 * @property phone the account phone
 * @property status the account status
 * @property taxIdentifier the account tax identifier
 */
class Account private constructor(
    val id: String? = null,
    val version: Long = 0L,
    val accountNumber: String,
    address: Address,
    balance: BigDecimal = BigDecimal.ZERO,
    name: Name,
    phone: Phone? = null,
    status: AccountStatus = AccountStatus.ACTIVE,
    taxIdentifier: TaxIdentifier,
) {
    var address: Address = address
        private set
    var balance: BigDecimal? = balance
        private set
    var name: Name = name
        private set
    var phone: Phone? = phone
        private set
    var status: AccountStatus? = status
        private set
    var taxIdentifier: TaxIdentifier = taxIdentifier
        private set

    /**
     * Reactivates an account if it is inactive.
     * @param accountGateway the gateway to save the account
     * @return the reactivated account
     */
    fun reactivate(accountGateway: AccountGateway) =
        apply {
            if (this.status == AccountStatus.ACTIVE) return this

            this.status = AccountStatus.ACTIVE
            return accountGateway.saveAccount(this)
        }

    /**
     * Deactivates an account if it is active.
     * @param accountGateway the gateway to save the account
     * @return the deactivated account
     */
    fun deactivate(accountGateway: AccountGateway) =
        apply {
            if (this.status == AccountStatus.INACTIVE) return this

            this.status = AccountStatus.INACTIVE
            return accountGateway.saveAccount(this)
        }

    /**
     * Updates the account with the given data.
     * @param accountGateway the gateway to save the account
     * @param address the new address
     * @param phone the new phone
     * @return the updated account
     */
    fun update(
        accountGateway: AccountGateway,
        address: Address?,
        phone: Phone?,
    ) = apply {
        this.address = address ?: this.address
        this.phone = phone ?: this.phone
        accountGateway.saveAccount(this)
    }

    companion object {
        fun of(
            id: String,
            version: Long,
            accountNumber: String,
            address: Address,
            balance: BigDecimal,
            name: Name,
            phone: Phone?,
            status: AccountStatus,
            taxIdentifier: TaxIdentifier,
        ): Account =
            Account(
                id = id,
                version = version,
                accountNumber = accountNumber,
                address = address,
                balance = balance,
                name = name,
                phone = phone,
                status = status,
                taxIdentifier = taxIdentifier,
            )

        /**
         * Creates an account with the given data.
         * @param accountGateway the gateway to save the account
         * @param accountToBeCreated the account data
         * @return the created account
         */
        fun create(
            accountGateway: AccountGateway,
            accountToBeCreated: AccountToBeCreated,
        ): Account {
            val accountHelpers = AccountHelpers(accountGateway)

            if (accountHelpers.isAccountCreated(accountToBeCreated.taxIdentifier)) {
                throw AccountAlreadyExistsException()
            }

            val account =
                Account(
                    accountNumber = accountHelpers.generateNextAccountNumber(),
                    address = accountToBeCreated.address,
                    name = accountToBeCreated.name,
                    phone = accountToBeCreated.phone,
                    taxIdentifier = accountToBeCreated.taxIdentifier,
                )

            return accountGateway.saveAccount(account)
        }

        private fun haveSufficientDataToUpdate(accountUpdates: AccountUpdates): Boolean =
            accountUpdates.address?.isNullOrBlank() == false || accountUpdates.phone?.isBlank() == false

        fun haveInsufficientDataToUpdate(accountUpdates: AccountUpdates): Boolean = !haveSufficientDataToUpdate(accountUpdates)
    }
}
