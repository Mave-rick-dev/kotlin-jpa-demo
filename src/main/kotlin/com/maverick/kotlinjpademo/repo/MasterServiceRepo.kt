package com.maverick.kotlinjpademo.repo

import com.maverick.kotlinjpademo.model.MasterService
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @Project kotlin-jpa-demo
 * @Author mave on 1/3/22
 */
interface MasterServiceRepo : JpaRepository<MasterService, Int>{
}