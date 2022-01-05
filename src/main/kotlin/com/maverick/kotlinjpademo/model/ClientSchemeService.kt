package com.maverick.kotlinjpademo.model

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

@Entity
@Table(name = "client_scheme_service")
class ClientSchemeService  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(
        name = "client_scheme_seq_gen",
        sequenceName = "client_scheme_seq_gen",
        allocationSize = 1
    )
    var id: Int = 0
    var limitValidation: Boolean = false
    var tranAmountLimit: Double = 0.0
    var tranLimit: Double = 0.0
    var performView: Boolean = false
    var performTransaction: Boolean = false
    var selfTransactionOnly: Boolean = false

    @JoinColumn(
        name = "client_scheme_id",
        referencedColumnName = "id"
    )
    @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    @JsonBackReference
    lateinit var clientScheme: ClientScheme

    @JoinColumn(
        name = "master_service_id",
        referencedColumnName = "id"
    )
    @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    @JsonBackReference
    lateinit var masterService: MasterService
}