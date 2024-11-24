package com.mdxco.c4bank.account.domain.account.entities.values

import com.mdxco.c4bank.account.domain.account.AccountGateway

@JvmInline
value class AccountNumber(val value: String) {
    companion object {
        /**
         * This method creates an account number from a string
         * @param value the account number value
         * @return the account number
         */
        fun fromString(value: String): AccountNumber = AccountNumber(value)

        /**
         * This method calculates the next account number based on the latest account number
         * @param latestAccountNumber the latest account number
         * @return the next account number
         */
        private fun getNextNumber(latestAccountNumber: String?): Long {
            val isSomeAccountCreated = latestAccountNumber != null

            return if (isSomeAccountCreated) {
                latestAccountNumber.replace("-", "").toLong() + 1
            } else {
                1L
            }
        }

        /**
         * This method formats the account number to the pattern XXXXXX-X
         * @param nextNumber the next account number
         * @return the formatted account number
         */
        private fun formatAccountNumber(nextNumber: Long): String =
            String.format("%06d-%d", nextNumber / 10, nextNumber % 10)

        /**
         * This method generates the next account number
         * @param accountGateway the gateway to get the latest account number
         * @return the next account number in the pattern XXXXXX-X
         */
        fun generateNextAccountNumber(accountGateway: AccountGateway): AccountNumber {
            val latestAccountNumber = accountGateway.getLatestAccountNumber()
            val nextNumber = getNextNumber(latestAccountNumber)
            val formattedNumber = formatAccountNumber(nextNumber)

            return fromString(formattedNumber)
        }
    }
}
