package com.mdxco.c4bank.account.infrastructure.models

import com.mdxco.c4bank.account.domain.account.entities.Account
import com.mdxco.c4bank.account.domain.account.entities.values.AccountNumber
import com.mdxco.c4bank.account.domain.account.entities.values.Name
import com.mdxco.c4bank.account.domain.account.entities.values.Phone
import com.mdxco.c4bank.account.domain.account.entities.values.TaxIdentifier
import com.mdxco.c4bank.account.domain.account.utils.AccountStatus
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
class AccountModel {
    @Version
    @Column(nullable = false)
    var version: Long = 0

    @Id
    @Column(nullable = false, unique = true)
    var id: String = ULID().nextULID()

    @Column(nullable = false, unique = true)
    lateinit var accountNumber: String

    @ManyToOne
    lateinit var address: AddressModel

    @Column(nullable = false)
    var balance: BigDecimal = BigDecimal.ZERO

    @Column(nullable = false)
    lateinit var name: String

    @Column
    var phone: String? = null

    @Column(nullable = false)
    var status: String = AccountStatus.ACTIVE.name

    @Column(nullable = false)
    lateinit var taxIdentifier: String

    fun toDomain() =
        Account.fromModel(
            accountNumber = AccountNumber.fromString(accountNumber),
            address = address.toDomain(),
            id = id,
            balance = balance,
            name = Name.fromString(name),
            phone = Phone.fromString(phone),
            status = AccountStatus.valueOf(status),
            taxIdentifier = TaxIdentifier.fromString(taxIdentifier),
            version = version,
        )

    companion object {
        fun of(account: Account) = AccountModel().apply {
            accountNumber = account.accountNumber.value
            address = AddressModel.of(account.address)
            balance = account.balance
            id = account.id ?: ULID().nextULID()
            name = account.name.value
            phone = account.phone?.value
            status = account.status?.name ?: AccountStatus.ACTIVE.name
            taxIdentifier = account.taxIdentifier.value
            version = account.version
        }
    }
}

fun Account.toModel() = AccountModel.of(this)
