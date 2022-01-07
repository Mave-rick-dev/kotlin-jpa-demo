package com.maverick.kotlinjpademo.exceptionHandler

/**
 * @Project kotlin-jpa-demo
 * @Author mave on 1/5/22
 */
class ClientSchemeServiceException(
    override var message: String
) : RuntimeException(message)
