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
import java.math.BigDecimal

@Entity
@Table(name = "account")
data class AccountModel(
//    @Version
//    @Column(nullable = false)
//    var version: Long = 0,

    @Id
    @Column(nullable = false, unique = true)
    val id: String,

    @Column(nullable = false, unique = true)
    val accountNumber: String,

    @ManyToOne
    val address: AddressModel,

    @Column(nullable = false)
    val balance: BigDecimal,

    @Column(nullable = false)
    val name: String,

    @Column
    val phone: String?,

    @Column(nullable = false)
    val status: String,

    @Column(nullable = false)
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
