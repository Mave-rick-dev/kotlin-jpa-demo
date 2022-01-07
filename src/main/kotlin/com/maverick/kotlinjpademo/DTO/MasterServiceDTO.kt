package com.maverick.kotlinjpademo.DTO

import com.maverick.kotlinjpademo.model.ClientScheme
import java.sql.Date

/**
 * @Project kotlin-jpa-demo
 * @Author mave on 1/3/22
 */
data class MasterServiceDTO(
    var isActive: Boolean,
    var serviceCode: String,
    var serviceNameLoc: String,
    var description: String,
    var isAvailable: Boolean,
    var clientScheme: ClientScheme?
)