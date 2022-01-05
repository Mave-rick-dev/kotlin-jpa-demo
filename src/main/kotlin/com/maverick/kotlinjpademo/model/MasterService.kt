package com.maverick.kotlinjpademo.model

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

/**
 * @Project kotlin-jpa-demo
 * @Author mave on 1/3/22
 */
@Entity
@Table(name = "master_service")
class MasterService{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(
        name = "master_service_seq_gen",
        sequenceName = "master_service_seq_gen",
        allocationSize = 1)
    var id: Int = 0
    var isActive: Boolean = false
    @Column(name = "service_code", unique = true, nullable = false)
    var serviceCode: String = ""
    var description: String = ""
    var isAvailable: Boolean = false
/*    @ManyToOne(fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL])
    @JoinColumn(name = "client_scheme_service_id")
    @JsonBackReference
    var clientSchemeService: ClientSchemeService? = null*/
 }