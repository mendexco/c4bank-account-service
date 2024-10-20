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
    summary = "Deactivate account",
    description = "Change account status to INACTIVE by account ID",
)
@Parameter(
    name = "accountId",
    description = "Account ULID identifier",
    example = "01JA3XGFHGFDXSNAPVM5DDH25J",
)
@ApiResponses(
    value = [
        ApiResponse(
            responseCode = "204",
            description = "Account deactivated successfully",
            content = [
                Content(
                    mediaType = "application/json",
                ),
            ],
        ),
        ApiResponse(
            responseCode = "404",
            description = "Account not found by informed ULID identifier",
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
annotation class DeactivateAccountDocs()
