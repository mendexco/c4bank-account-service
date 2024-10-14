package com.mdxco.c4bank.application.web.docs

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Operation(
    summary = "Update account information",
    description = "Update account address and/or phone number by account ID"
)
@ApiResponses(
    value = [
        ApiResponse(
            responseCode = "204",
            description = "Account updated successfully",
            content = [Content(
                mediaType = "application/json",
            )],
        ),
        ApiResponse(
            responseCode = "400",
            description = "Invalid request data",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(
                    example = """{
                        "code": "INVALID_ARGUMENT",
                        "message": "One or more fields were incorrectly informed",
                        "body": {
                            "phone": [
                              "Invalid field",
                              "Field length not valid"
                            ]
                        }
                    }"""
                )
            )],
        ),
        ApiResponse(
            responseCode = "422",
            description = "Insufficient data to update account",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(
                    example = """{
                        "code": "INSUFFICIENT_DATA_TO_UPDATE",
                        "message": "No data to update account was provided"
                    }"""
                )
            )]
        ),
        ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(
                    example = """{
                        "code": "INTERNAL_SERVER_ERROR",
                        "message": "An unexpected error occurred"
                    }"""
                )
            )],
        )
    ]
)
annotation class UpdateAccountDocs()
