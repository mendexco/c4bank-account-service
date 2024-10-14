package com.mdxco.c4bank.domain.account.entities

data class Address(
    val id: String? = null,
    val city: String,
    val country: String,
    val neighborhood: String,
    val number: String,
    val postalCode: String,
    val state: String,
    val street: String
) {
    fun isNullOrBlank() =
        listOf(id, city, country, neighborhood, number, postalCode, state, street).all { it.isNullOrBlank() }

    fun isEqual(other: Address?): Boolean {
        return this.copy(id = null) == other?.copy(id = null)
    }
}
