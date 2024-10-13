package com.mdxco.c4bank.domain.account.exceptions

enum class ErrorCodes(val message: String) {
    INTERNAL_SERVER_ERROR("An unexpected error occurred"),
    INVALID_REQUEST_DATA("One or more fields were incorrectly informed"),
    ACCOUNT_ALREADY_EXISTS("Account with informed tax identifier already exists")
}