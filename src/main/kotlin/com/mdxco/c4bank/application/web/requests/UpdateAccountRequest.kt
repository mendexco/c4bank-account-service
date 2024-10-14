package com.mdxco.c4bank.application.web.requests

import com.mdxco.c4bank.commons.constants.RegexpMatches
import com.mdxco.c4bank.commons.constants.ResponseMessages
import com.mdxco.c4bank.domain.account.entities.AccountUpdate
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import org.hibernate.validator.constraints.Length

data class UpdateAccountRequest(
    @Schema(description = "Account owner address")
    val address: AccountAddressRequest? = null,

    @field:NotNull(message = ResponseMessages.FIELD_REQUIRED)
    @field:NotBlank(message = ResponseMessages.FIELD_NOT_BLANK)
    @Schema(description = "Account ULID identifier", example = "01JA3XGFHGFDXSNAPVM5DDH25J")
    var id: String? = null,

    @field:Length(min = 9, max = 12, message = ResponseMessages.FIELD_LENGTH_NOT_VALID)
    @field:Pattern(regexp = RegexpMatches.PHONE_NUMBER, message = ResponseMessages.FIELD_INVALID)
    @Schema(description = "Phone number without country code", example = "11988885555")
    var phone: String? = null,
) {
    init {
        id = id!!.trim()
        phone = phone?.trim()
    }

    fun toDomain() = AccountUpdate(
        address = address?.toDomain(),
        id = id!!,
        phone = phone,
    )
}
