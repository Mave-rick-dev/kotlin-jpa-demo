package com.maverick.kotlinjpademo.mapper

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

/**
 * @Project kotlin-jpa-demo
 * @Author mave on 1/6/22
 */
@Mapper
interface ClientSchemeServiceMapper {

    @Select("  SELECT master_service_id\n" +
            "        FROM client_scheme_service\n" +
            "        WHERE client_scheme_id=#{clientSchemeId}")
    fun getClientSchemes(@Param("clientSchemeId") clientSchemeId: Int): List<Int>
}