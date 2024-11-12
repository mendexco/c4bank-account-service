package com.mdxco.c4bank.account.domain.account

import com.mdxco.c4bank.account.domain.account.entities.Account
import com.mdxco.c4bank.account.domain.account.entities.values.TaxIdentifier

interface AccountGateway {
    /**
     * Checks if an account is created.
     * @param taxIdentifier the account tax identifier
     * @return true if the account is created, false otherwise
     */
    fun checkIfAccountIsCreated(taxIdentifier: TaxIdentifier): Boolean

    /**
     * Saves an account.
     * @param account the account to save
     * @return the saved account
     */
    fun saveAccount(account: Account): Account

    /**
     * Gets the latest account number.
     * @return the latest account number
     */
    fun getLatestAccountNumber(): String?

    /**
     * Gets an account by its id.
     * @param id the account id
     * @return the account
     */
    fun getById(id: String): Account?
}
