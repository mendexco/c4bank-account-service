package com.mdxco.c4bank.application.requests

import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class AccountDataRequest(
    @Valid @NotBlank(message = "Name cannot be blank")
    val name: String,

    @field:Pattern(regexp = "^[+]?[0-9]{10,13}\$", message = "Invalid phone number")
    val phone: String,
)
