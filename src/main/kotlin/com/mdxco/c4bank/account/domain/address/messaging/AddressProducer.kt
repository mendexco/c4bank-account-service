package com.mdxco.c4bank.account.domain.address.messaging

interface AddressProducer {
    fun verifyAddressUses(addressId: String)
}
