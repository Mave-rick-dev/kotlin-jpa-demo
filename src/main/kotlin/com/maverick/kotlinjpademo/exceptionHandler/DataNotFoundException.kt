package com.maverick.kotlinjpademo.exceptionHandler

import java.util.function.Supplier

/**
 * @Project kotlin-jpa-demo
 * @Author mave on 1/6/22
 */
class DataNotFoundException(
    override var message: String?
) : RuntimeException(message) {
}