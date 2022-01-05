package com.maverick.kotlinjpademo.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import java.sql.Date
import javax.persistence.*

/**
 * @Project kotlin-jpa-demo
 * @Author mave on 1/3/22
 */
@Entity
@Table(name = "client_scheme")
class ClientScheme{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(
    name = "client_scheme_seq_gen",
    sequenceName = "client_scheme_seq_gen",
    allocationSize = 1
    )
    var id: Int = 0
    var isActive: Boolean = false
    @Column(name = "name", unique = true, nullable = false)
    var name: String= ""
    var tranAmountLimit: Double = 0.0
    var tranLimit: Double = 0.0
    /*@OneToMany(fetch = FetchType.LAZY,
        mappedBy = "clientScheme",
        orphanRemoval = true,
        cascade = [CascadeType.ALL])
    @JsonManagedReference
    var masterServices: MutableList<MasterService> = mutableListOf()*/
 }