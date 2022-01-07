package com.maverick.kotlinjpademo

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScans

@SpringBootApplication
class KotlinJpaDemoApplication

fun main(args: Array<String>) {
	runApplication<KotlinJpaDemoApplication>(*args)
}
