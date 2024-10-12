package com.mdxco.c4bank.domain.account.exceptions

class AccountAlreadyExists(taxIdentifier: String) : RuntimeException("Account already exists: $taxIdentifier")