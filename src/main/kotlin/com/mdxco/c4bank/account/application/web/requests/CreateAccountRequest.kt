package com.mdxco.c4bank.account.application.web.requests

import com.mdxco.c4bank.account.commons.constants.ResponseMessages
import com.mdxco.c4bank.account.domain.account.entities.AccountToBeCreated
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull

data class CreateAccountRequest(
    @field:NotNull(message = ResponseMessages.FIELD_REQUIRED)
    @Schema(description = "Account owner address")
    val address: AccountAddressRequest? = null,
    @Schema(description = "Account owner full name", example = "Yuri Alberto")
    var name: String?,
    @Schema(description = "Phone number without country code", example = "12928769990")
    var phone: String?,
    @Schema(description = "Account owner tax identifier", example = "22266611154")
    var taxIdentifier: String?,
) {
    fun toDomain() =
        AccountToBeCreated.of(
            address = address!!.toDomain(),
            name = name,
            phone = phone,
            taxIdentifier = taxIdentifier,
        )
}
