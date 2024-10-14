package com.mdxco.c4bank.application.web.requests

import com.mdxco.c4bank.commons.constants.RegexpMatches
import com.mdxco.c4bank.commons.constants.ResponseMessages
import com.mdxco.c4bank.domain.account.entities.Account
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import org.hibernate.validator.constraints.Length

data class CreateAccountRequest(
    @field:NotNull(message = ResponseMessages.FIELD_REQUIRED)
    @Schema(description = "Account owner address")
    val address: AccountAddressRequest? = null,

    @field:NotBlank(message = ResponseMessages.FIELD_NOT_BLANK)
    @field:NotNull(message = ResponseMessages.FIELD_REQUIRED)
    @field:Length(min = 2, max = 120, message = ResponseMessages.FIELD_LENGTH_NOT_VALID)
    @field:Pattern(
        // '\\p{L}' allows letters from any language, and '\\p{Z}' allows whitespace separators
        regexp = RegexpMatches.NAME, message = ResponseMessages.CANNOT_CONTAIN_NUMBER_OR_SPECIAL
    )
    @Schema(description = "Account owner full name", example = "Yuri Alberto")
    var name: String? = null,

    @field:Length(min = 9, max = 12, message = ResponseMessages.FIELD_LENGTH_NOT_VALID)
    @field:Pattern(regexp = RegexpMatches.PHONE_NUMBER, message = ResponseMessages.FIELD_INVALID)
    @Schema(description = "Phone number without country code", example = "12928769990")
    var phone: String?,

    @field:NotNull(message = ResponseMessages.FIELD_REQUIRED)
    @field:NotBlank(message = ResponseMessages.FIELD_NOT_BLANK)
    @field:Pattern(regexp = RegexpMatches.TAX_IDENTIFIER, message = ResponseMessages.FIELD_INVALID)
    @Schema(description = "Account owner tax identifier", example = "22266611154")
    var taxIdentifier: String? = null
) {
    init {
        name = name!!.trim()
        phone = phone!!.trim()
        taxIdentifier = taxIdentifier!!.trim()
    }

    fun toDomain() = Account(
        address = address!!.toDomain(),
        name = name!!,
        phone = phone,
        taxIdentifier = taxIdentifier!!
    )
}
