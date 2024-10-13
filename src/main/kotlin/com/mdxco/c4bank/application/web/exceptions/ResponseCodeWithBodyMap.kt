package com.mdxco.c4bank.application.web.exceptions

data class ResponseCodeWithBodyMap(
    val code: String,
    val message: String,
    val body: Map<String, List<String>>
)