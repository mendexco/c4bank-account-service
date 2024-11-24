package com.mdxco.c4bank.account.infrastructure.models

import com.mdxco.c4bank.account.domain.address.entities.Address
import com.mdxco.c4bank.account.domain.address.entities.values.City
import com.mdxco.c4bank.account.domain.address.entities.values.Country
import com.mdxco.c4bank.account.domain.address.entities.values.Neighborhood
import com.mdxco.c4bank.account.domain.address.entities.values.Number
import com.mdxco.c4bank.account.domain.address.entities.values.PostalCode
import com.mdxco.c4bank.account.domain.address.entities.values.State
import com.mdxco.c4bank.account.domain.address.entities.values.Street
import de.huxhorn.sulky.ulid.ULID
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "address")
class AddressModel {
    @Id
    @Column(nullable = false, unique = true)
    var id: String = ULID().nextULID()

    @Column(nullable = false)
    lateinit var city: String

    @Column(nullable = false)
    lateinit var country: String

    @Column(nullable = false)
    lateinit var neighborhood: String

    @Column(nullable = false)
    lateinit var number: String

    @Column(nullable = false)
    lateinit var postalCode: String

    @Column(nullable = false)
    lateinit var state: String

    @Column(nullable = false)
    lateinit var street: String

    fun toDomain() =
        Address.of(
            city = City.fromString(city),
            country = Country.fromString(country),
            id = id,
            neighborhood = Neighborhood.fromString(neighborhood),
            number = Number.fromString(number),
            postalCode = PostalCode.fromString(postalCode),
            state = State.fromString(state),
            street = Street.fromString(street),
        )

    companion object {
        fun of(address: Address) = AddressModel().apply {
            id = address.id ?: ULID().nextULID()
            city = address.city.value
            country = address.country.value
            neighborhood = address.neighborhood.value
            number = address.number.value
            postalCode = address.postalCode.value
            state = address.state.value
            street = address.street.value
        }
    }
}

fun Address.toModel() = AddressModel.of(this)

