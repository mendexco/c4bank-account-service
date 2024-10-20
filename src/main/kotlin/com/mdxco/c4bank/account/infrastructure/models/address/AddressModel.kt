package com.mdxco.c4bank.account.infrastructure.models.address

import com.mdxco.c4bank.account.domain.account.entities.Address
import de.huxhorn.sulky.ulid.ULID
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "address")
data class AddressModel(
    @Id
    @Column(nullable = false, unique = true)
    val id: String,
    @Column(nullable = false)
    val city: String,
    @Column(nullable = false)
    val country: String,
    @Column(nullable = false)
    val neighborhood: String,
    @Column(nullable = false)
    val number: String,
    @Column(nullable = false)
    val postalCode: String,
    @Column(nullable = false)
    val state: String,
    @Column(nullable = false)
    val street: String,
) {
    fun toDomain() =
        Address(
            city = city,
            country = country,
            id = id,
            neighborhood = neighborhood,
            number = number,
            postalCode = postalCode,
            state = state,
            street = street,
        )
}

fun Address.toModel() =
    AddressModel(
        id = id ?: ULID().nextULID(),
        city = city,
        country = country,
        neighborhood = neighborhood,
        number = number,
        postalCode = postalCode,
        state = state,
        street = street,
    )
