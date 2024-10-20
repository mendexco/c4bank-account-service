package com.mdxco.c4bank.account.application.web.requests

import com.mdxco.c4bank.account.commons.constants.RegexpMatches
import com.mdxco.c4bank.account.commons.constants.ResponseMessages
import com.mdxco.c4bank.account.domain.account.entities.AccountUpdate
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Pattern
import org.hibernate.validator.constraints.Length

data class UpdateAccountRequest(
    @Schema(description = "Account owner address")
    val address: com.mdxco.c4bank.account.application.web.requests.AccountAddressRequest? = null,

    @field:Length(min = 9, max = 12, message = ResponseMessages.FIELD_LENGTH_NOT_VALID)
    @field:Pattern(regexp = RegexpMatches.PHONE_NUMBER, message = ResponseMessages.FIELD_INVALID)
    @Schema(description = "Phone number without country code", example = "11988885555")
    var phone: String? = null,
) {
    init {
        phone = phone?.trim()
    }

    fun toDomain() = AccountUpdate(
        address = address?.toDomain(),
        phone = phone,
    )
}
