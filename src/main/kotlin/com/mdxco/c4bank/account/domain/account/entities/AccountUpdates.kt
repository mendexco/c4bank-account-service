package com.mdxco.c4bank.account.domain.account.entities

import com.mdxco.c4bank.account.domain.account.entities.values.Phone
import com.mdxco.c4bank.account.domain.address.entities.Address

class AccountUpdates private constructor(
    val address: Address? = null,
    val phone: Phone? = null,
) {
    companion object {
        fun of(
            address: Address? = null,
            phone: String? = null,
        ) = AccountUpdates(
            address = address,
            phone = Phone.fromString(phone),
        )
    }
}
