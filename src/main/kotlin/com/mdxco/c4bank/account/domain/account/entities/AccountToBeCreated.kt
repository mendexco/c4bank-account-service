package com.mdxco.c4bank.account.domain.account.entities

import com.mdxco.c4bank.account.domain.account.AccountGateway
import com.mdxco.c4bank.account.domain.account.entities.values.AccountNumber
import com.mdxco.c4bank.account.domain.account.entities.values.Name
import com.mdxco.c4bank.account.domain.account.entities.values.Phone
import com.mdxco.c4bank.account.domain.account.entities.values.TaxIdentifier
import com.mdxco.c4bank.account.domain.address.AddressGateway
import com.mdxco.c4bank.account.domain.address.entities.Address
import com.mdxco.c4bank.account.domain.address.entities.AddressToBeCreated

class AccountToBeCreated private constructor(
    val address: AddressToBeCreated,
    val name: Name,
    val phone: Phone? = null,
    val taxIdentifier: TaxIdentifier,
) {
    fun toEntity(
        accountGateway: AccountGateway,
        addressGateway: AddressGateway
    ) =
        Account.of(
            accountNumber = AccountNumber.generateNextAccountNumber(accountGateway),
            address = Address.create(addressGateway, address),
            name = name,
            phone = phone,
            taxIdentifier = taxIdentifier,
        )

    companion object {
        fun of(
            address: AddressToBeCreated,
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
