package com.mdxco.c4bank.account.domain.account.entities

import com.mdxco.c4bank.account.domain.account.entities.values.Phone
import com.mdxco.c4bank.account.domain.account.exceptions.InsufficientDataToUpdateException
import com.mdxco.c4bank.account.domain.address.entities.AddressToBeCreated

class AccountUpdates private constructor(
    val address: AddressToBeCreated? = null,
    val phone: Phone? = null,
) {
    init {
        require(haveSufficientDataToUpdate(address, phone)) { throw InsufficientDataToUpdateException() }
    }

    companion object {
        fun of(
            address: AddressToBeCreated? = null,
            phone: String? = null,
        ) = AccountUpdates(
            address = address,
            phone = Phone.fromString(phone),
        )

        /**
         * Updates the account with the given data.
         * @param address the account address to update
         * @param phone the account phone to update
         * @return true if the account has sufficient data to be updated, false otherwise
         */
        private fun haveSufficientDataToUpdate(address: AddressToBeCreated?, phone: Phone?) =
            address?.isNullOrBlank() == false || phone?.isBlank() == false
    }
}
