package com.mdxco.c4bank.application.requests

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class AccountDataRequest(
    @field:NotBlank(message = "Name cannot be blank")
    @field:Pattern(
        // '\\p{L}' allows letters from any language, and '\\p{Z}' allows whitespace separators
        regexp = "^[\\p{L}\\p{Z}]+$", message = "Name cannot contain emojis"
    )
    @Schema(description = "Account owner full name", example = "André Cocão")
    var name: String,

    @field:Pattern(regexp = "^\\d{9,12}$", message = "Invalid phone number")
    @Schema(description = "Phone number without country code", example = "11988885555")
    var phone: String,
) {
    init {
        name = name.trim()
        phone = phone.trim()
    }
}
