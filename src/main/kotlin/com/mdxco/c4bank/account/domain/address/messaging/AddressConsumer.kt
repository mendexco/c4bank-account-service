package com.mdxco.c4bank.account.domain.address.messaging

interface AddressConsumer {
    fun verifyAddressUses(addressId: Map<String, String>)
}
