package com.mdxco.c4bank.infrastructure.h2.repositories.address.models

import com.mdxco.c4bank.domain.account.entities.Address
import de.huxhorn.sulky.ulid.ULID
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "address")
data class AddressModel(
    @Id
    @Column(name = "id", unique = true, nullable = false)
    val id: String,

    @Column(name = "city", nullable = false)
    val city: String,

    @Column(name = "country", nullable = false)
    val country: String,

    @Column(name = "neighborhood", nullable = false)
    val neighborhood: String,

    @Column(name = "number", nullable = false)
    val number: String,

    @Column(name = "postal_code", nullable = false)
    val postalCode: String,

    @Column(name = "state", nullable = false)
    val state: String,

    @Column(name = "street", nullable = false)
    val street: String
) {
    fun toDomain() = Address(
        city = city,
        country = country,
        id = id,
        neighborhood = neighborhood,
        number = number,
        postalCode = postalCode,
        state = state,
        street = street
    )
}

fun Address.toModel() = AddressModel(
    id = id ?: ULID().nextULID(),
    city = city,
    country = country,
    neighborhood = neighborhood,
    number = number,
    postalCode = postalCode,
    state = state,
    street = street
)