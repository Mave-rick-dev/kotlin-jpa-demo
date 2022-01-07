package com.maverick.kotlinjpademo.exceptionHandler

import org.hibernate.MappingException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.NoSuchElementException

/**
 * @Project kotlin-jpa-demo
 * @Author mave on 1/5/22
 */
@RestControllerAdvice
class CustomExceptionHandler {

    @ExceptionHandler(IllegalArgumentException::class)
    fun illegalArgumentExceptionHandler(exception: IllegalArgumentException): ResponseEntity<GlobalApiResponse>{
        return ResponseEntity.ok(GlobalApiResponse(
            HttpStatus.BAD_REQUEST,
            exception.message
        ) )
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun dataNotFoundExceptionHandler(exception: NoSuchElementException): ResponseEntity<GlobalApiResponse>{
        return ResponseEntity.ok(GlobalApiResponse(
            HttpStatus.BAD_REQUEST,
            exception.message
        ) )
    }

    @ExceptionHandler(MappingException::class)
    fun mappingExceptionHandler(exception: MappingException): ResponseEntity<GlobalApiResponse>{
        return ResponseEntity.ok(GlobalApiResponse(
            HttpStatus.BAD_REQUEST,
            exception.message
        ) )
    }
}