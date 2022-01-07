package com.maverick.kotlinjpademo.exceptionHandler

import org.springframework.http.HttpStatus

/**
 * @Project kotlin-jpa-demo
 * @Author mave on 1/5/22
 */
data class GlobalApiResponse(
    val status: HttpStatus,
    val message: String?,
    val code: Int = status.value()
)
