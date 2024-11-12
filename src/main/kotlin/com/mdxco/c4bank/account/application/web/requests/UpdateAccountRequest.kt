package com.mdxco.c4bank.account.application.web.requests

import com.mdxco.c4bank.account.domain.account.entities.AccountUpdates
import io.swagger.v3.oas.annotations.media.Schema

data class UpdateAccountRequest(
    @Schema(description = "Account owner address")
    val address: AccountAddressRequest?,
    @Schema(description = "Phone number without country code", example = "11988885555")
    var phone: String?,
) {
    fun toDomain() =
        AccountUpdates.of(
            address = address?.toDomain(),
            phone = phone,
        )
}
