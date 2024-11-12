package com.mdxco.c4bank.account.infrastructure.models.account

import com.mdxco.c4bank.account.domain.account.entities.Account
import com.mdxco.c4bank.account.domain.account.entities.values.Name
import com.mdxco.c4bank.account.domain.account.entities.values.Phone
import com.mdxco.c4bank.account.domain.account.entities.values.TaxIdentifier
import com.mdxco.c4bank.account.domain.account.utils.AccountStatus
import com.mdxco.c4bank.account.infrastructure.models.address.AddressModel
import com.mdxco.c4bank.account.infrastructure.models.address.toModel
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
    @Column(nullable = false)
    val version: Long,
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
    val name: Name,
    @Column
    val phone: Phone?,
    @Column(nullable = false)
    val status: String,
    @Column(nullable = false)
    val taxIdentifier: TaxIdentifier,
) {
    fun toDomain() =
        Account.of(
            accountNumber = accountNumber,
            address = address.toDomain(),
            id = id,
            balance = balance,
            name = name,
            phone = phone,
            status = AccountStatus.valueOf(status),
            taxIdentifier = taxIdentifier,
            version = version,
        )
}

fun Account.toModel() =
    AccountModel(
        accountNumber = accountNumber,
        address = address.toModel(),
        id = id ?: ULID().nextULID(),
        balance = balance ?: BigDecimal.ZERO,
        name = name,
        phone = phone,
        status = status?.name ?: AccountStatus.ACTIVE.name,
        taxIdentifier = taxIdentifier,
        version = version,
    )
