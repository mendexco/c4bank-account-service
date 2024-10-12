package com.mdxco.c4bank.infrastructure.h2.repositories.account.models

import com.mdxco.c4bank.domain.account.entities.Account
import com.mdxco.c4bank.domain.account.entities.enums.AccountStatus
import de.huxhorn.sulky.ulid.ULID
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "account")
data class AccountModel(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true, nullable = false)
    val id: String,

    @Column(name = "balance", nullable = false)
    val balance: BigDecimal,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "phone", nullable = false)
    val phone: String,

    @Column(name = "status", nullable = false)
//    val status: String = AccountStatus.ACTIVE.name,
    val status: String,

    @Column(name = "tax_identifier", nullable = false)
    val taxIdentifier: String,
) {
    fun toDomain() = Account(
//        address = AccountAddress(),
        id = id,
        balance = balance,
        name = name,
        phone = phone,
        status = AccountStatus.valueOf(status),
        taxIdentifier = taxIdentifier
    )
}

fun Account.toModel() = AccountModel(
//    address = address,
    id = id ?: ULID().nextULID(),
    balance = balance ?: BigDecimal.ZERO,
    name = name,
    phone = phone,
    status = status?.name ?: AccountStatus.ACTIVE.name,
    taxIdentifier = taxIdentifier
)