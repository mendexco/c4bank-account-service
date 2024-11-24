package com.mdxco.c4bank.account.application.web.docs

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Operation(
    summary = "Get an account",
    description = "Get an account data by its ULID identifier",
)
@Parameter(
    name = "accountId",
    description = "Account ULID identifier",
    example = "01JA3XGFHGFDXSNAPVM5DDH25J",
)
@ApiResponses(
    value = [
        ApiResponse(
            responseCode = "200",
            description = "Account returned with success",
            content = [
                Content(
                    mediaType = "application/json",
                    schema =
                        Schema(
                            example = """{
                        "accountNumber": "000000-1",
                        "balance": 0,
                        "name": "Yuri Alberto",
                        "phone": "12928769990",
                        "status": "ACTIVE",
                        "taxIdentifier": "22266611154"
                    }""",
                        ),
                ),
            ],
        ),
        ApiResponse(
            responseCode = "404",
            description = "Account not found by provided identifier",
            content = [
                Content(
                    mediaType = "application/json",
                    schema =
                        Schema(
                            example = """{
                        "code": "ACCOUNT_NOT_FOUND",
                        "message": "Account not found by informed ID"
                    }""",
                        ),
                ),
            ],
        ),
        ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = [
                Content(
                    mediaType = "application/json",
                    schema =
                        Schema(
                            example = """{
                        "code": "INTERNAL_SERVER_ERROR",
                        "message": "An unexpected error occurred"
                    }""",
                        ),
                ),
            ],
        ),
    ],
)
annotation class GetAccountDocs()
