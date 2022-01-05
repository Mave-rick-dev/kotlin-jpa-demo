package com.maverick.kotlinjpademo.DTO

import com.fasterxml.jackson.annotation.JsonInclude
import java.sql.Date

/**
 * @Project kotlin-jpa-demo
 * @Author mave on 1/3/22
 */
data class ClientSchemeServiceDTO(
    var id: Int?,
    var limitValidation: Boolean,
    var tranAmountLimit: Double,
    var tranLimit: Double,
    var performView: Boolean,
    var performTransaction: Boolean,
    var selfTransactionOnly: Boolean,
    var clientSchemeId: Int,
    var masterServiceIds: MutableList<Int>
) {

}