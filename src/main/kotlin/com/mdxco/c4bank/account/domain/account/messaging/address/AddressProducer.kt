package com.mdxco.c4bank.account.domain.account.messaging.address

interface AddressProducer {
    fun verifyAddressUses(addressId: String)
}
