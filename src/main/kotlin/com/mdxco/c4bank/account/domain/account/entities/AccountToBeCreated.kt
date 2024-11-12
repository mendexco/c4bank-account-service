package com.mdxco.c4bank.account.domain.account.entities

import com.mdxco.c4bank.account.domain.account.entities.values.Name
import com.mdxco.c4bank.account.domain.account.entities.values.Phone
import com.mdxco.c4bank.account.domain.account.entities.values.TaxIdentifier
import com.mdxco.c4bank.account.domain.address.entities.Address

class AccountToBeCreated private constructor(
    address: Address,
    val name: Name,
    val phone: Phone? = null,
    val taxIdentifier: TaxIdentifier,
) {
    var address: Address = address
        private set

    fun updateAddress(address: Address) =
        apply {
            this.address = address
        }

    companion object {
        fun of(
            address: Address,
            name: String?,
            phone: String?,
            taxIdentifier: String?,
        ) = AccountToBeCreated(
            address = address,
            name = Name.fromString(name),
            phone = Phone.fromString(phone),
            taxIdentifier = TaxIdentifier.fromString(taxIdentifier),
        )
    }
}
