package com.maverick.kotlinjpademo.DTO

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import java.sql.Date

/**
 * @Project kotlin-jpa-demo
 * @Author mave on 1/3/22
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
data class ClientSchemeDTO(
    var id: Int,
    var isActive: Boolean,
    var name: String,
    var tranAmountLimit: Double,
    var tranLimit: Double
)