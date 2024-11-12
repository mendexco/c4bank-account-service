package com.mdxco.c4bank.account.commons.helpers

import com.mdxco.c4bank.account.domain.account.AccountGateway
import com.mdxco.c4bank.account.domain.account.entities.values.TaxIdentifier

class AccountHelpers(
    private val accountGateway: AccountGateway,
) {
    /**
     * This method calculates the next account number based on the latest account number
     * @param latestAccountNumber the latest account number
     * @return the next account number
     */
    private fun getNextNumber(latestAccountNumber: String?): Long {
        val isSomeAccountCreated = latestAccountNumber != null

        return if (isSomeAccountCreated) {
            latestAccountNumber!!.replace("-", "").toLong() + 1
        } else {
            1L
        }
    }

    /**
     * This method formats the account number to the pattern XXXXXX-X
     * @param nextNumber the next account number
     * @return the formatted account number
     */
    private fun formatAccountNumber(nextNumber: Long): String = String.format("%06d-%d", nextNumber / 10, nextNumber % 10)

    /**
     * This method generates the next account number
     * @return the next account number in the pattern XXXXXX-X
     */
    fun generateNextAccountNumber(): String {
        val latestAccountNumber = accountGateway.getLatestAccountNumber()
        val nextNumber = getNextNumber(latestAccountNumber)
        val formattedNumber = formatAccountNumber(nextNumber)

        return formattedNumber
    }

    /**
     * This method checks if an account is created
     * @param taxIdentifier the tax identifier of the account
     * @return true if the account is created, false otherwise
     */
    fun isAccountCreated(taxIdentifier: TaxIdentifier): Boolean = accountGateway.checkIfAccountIsCreated(taxIdentifier)
}
