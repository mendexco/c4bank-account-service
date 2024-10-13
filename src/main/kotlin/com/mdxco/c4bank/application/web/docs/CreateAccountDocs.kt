package com.mdxco.c4bank.application.web.docs

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Operation(summary = "Create a new account", description = "Create a new account with the provided data")
@ApiResponses(
    value = [
        ApiResponse(
            responseCode = "201",
            description = "Account created successfully",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(
                    example = """{
                        "accountNumber": "123456-7"
                    }"""
                )
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
            responseCode = "409",
            description = "Account already exists",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(
                    example = """{
                        "code": "ACCOUNT_ALREADY_EXISTS",
                        "message": "Account with informed tax identifier already exists"
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
annotation class CreateAccountDocs()
