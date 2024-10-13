package com.mdxco.c4bank.infrastructure.models.account

import com.mdxco.c4bank.domain.account.entities.Account
import com.mdxco.c4bank.domain.account.entities.enums.AccountStatus
import com.mdxco.c4bank.infrastructure.models.address.AddressModel
import com.mdxco.c4bank.infrastructure.models.address.toModel
import de.huxhorn.sulky.ulid.ULID
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.persistence.Version
import java.math.BigDecimal

@Entity
@Table(name = "account")
data class AccountModel(
    @Version
    @Column(name = "version", nullable = false)
    var version: Long = 0,

    @Id
    @Column(name = "id", unique = true, nullable = false)
    val id: String,

    @Column(name = "account_number", unique = true, nullable = false)
    val accountNumber: String,

    @ManyToOne
    val address: AddressModel,

    @Column(name = "balance", nullable = false)
    val balance: BigDecimal,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "phone", nullable = true)
    val phone: String?,

    @Column(name = "status", nullable = false)
    val status: String,

    @Column(name = "tax_identifier", nullable = false)
    val taxIdentifier: String
) {
    fun toDomain() = Account(
        accountNumber = accountNumber,
        address = address.toDomain(),
        id = id,
        balance = balance,
        name = name,
        phone = phone,
        status = AccountStatus.valueOf(status),
        taxIdentifier = taxIdentifier
    )
}

fun Account.toModel() = AccountModel(
    accountNumber = accountNumber!!,
    address = address.toModel(),
    id = id ?: ULID().nextULID(),
    balance = balance ?: BigDecimal.ZERO,
    name = name,
    phone = phone,
    status = status?.name ?: AccountStatus.ACTIVE.name,
    taxIdentifier = taxIdentifier
)