package com.mdxco.c4bank.account.domain.account.entities

import com.mdxco.c4bank.account.domain.account.AccountGateway
import com.mdxco.c4bank.account.domain.account.entities.values.AccountNumber
import com.mdxco.c4bank.account.domain.account.entities.values.Name
import com.mdxco.c4bank.account.domain.account.entities.values.Phone
import com.mdxco.c4bank.account.domain.account.entities.values.TaxIdentifier
import com.mdxco.c4bank.account.domain.account.exceptions.AccountAlreadyExistsException
import com.mdxco.c4bank.account.domain.account.exceptions.AccountNotFoundException
import com.mdxco.c4bank.account.domain.account.utils.AccountStatus
import com.mdxco.c4bank.account.domain.address.AddressGateway
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
    val accountNumber: AccountNumber,
    address: Address,
    val balance: BigDecimal = BigDecimal.ZERO,
    val name: Name,
    phone: Phone? = null,
    status: AccountStatus = AccountStatus.ACTIVE,
    val taxIdentifier: TaxIdentifier,
) {
    var address: Address = address
        private set
    var phone: Phone? = phone
        private set
    var status: AccountStatus? = status
        private set

    /**
     * Reactivates an account if it is inactive.
     * @param accountGateway the gateway to save the account
     * @return the reactivated account
     */
    fun reactivate(accountGateway: AccountGateway) = run {
        if (this.status == AccountStatus.ACTIVE) this

        this.status = AccountStatus.ACTIVE
        accountGateway.saveAccount(this)
    }

    /**
     * Deactivates an account if it is active.
     * @param accountGateway the gateway to save the account
     * @return the deactivated account
     */
    fun deactivate(accountGateway: AccountGateway) = run {
        if (this.status == AccountStatus.INACTIVE) this

        this.status = AccountStatus.INACTIVE
        accountGateway.saveAccount(this)
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
    ) = run {
        this.address = address ?: this.address
        this.phone = phone ?: this.phone
        accountGateway.saveAccount(this)
    }

    companion object {
        fun of(
            id: String? = null,
            version: Long = 0L,
            accountNumber: AccountNumber,
            address: Address,
            balance: BigDecimal = BigDecimal.ZERO,
            name: Name,
            phone: Phone? = null,
            status: AccountStatus = AccountStatus.ACTIVE,
            taxIdentifier: TaxIdentifier,
        ) = Account(
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
         * Gets an account by its ULID id.
         * @param accountGateway the gateway to get the account
         * @param id the ULID id of the account to be retrieved
         * @return the account that was retrieved
         * @throws AccountNotFoundException if the account was not found
         */
        fun get(
            accountGateway: AccountGateway,
            id: String,
        ) = accountGateway.getById(id) ?: throw AccountNotFoundException()

        /**
         * Creates an account with the given data.
         * @param accountGateway the gateway to save the account
         * @param addressGateway the gateway to save the address
         * @param accountToBeCreated the account data
         * @return the created account
         * @throws AccountAlreadyExistsException if the account already exists
         */
        fun create(
            accountGateway: AccountGateway,
            addressGateway: AddressGateway,
            accountToBeCreated: AccountToBeCreated,
        ) = run {
            if (isAccountCreated(accountGateway, accountToBeCreated.taxIdentifier)) {
                throw AccountAlreadyExistsException()
            }

            accountGateway.saveAccount(
                accountToBeCreated.toEntity(
                    accountGateway,
                    addressGateway
                )
            )
        }

        /**
         * This method checks if an account is created
         * @param accountGateway the gateway to check if the account is created
         * @param taxIdentifier the tax identifier of the account
         * @return true if the account is created, false otherwise
         */
        fun isAccountCreated(accountGateway: AccountGateway, taxIdentifier: TaxIdentifier) =
            accountGateway.checkIfAccountIsCreated(taxIdentifier)
    }
}
