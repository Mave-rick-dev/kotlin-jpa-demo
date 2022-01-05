package com.maverick.kotlinjpademo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinJpaDemoApplication

fun main(args: Array<String>) {
	runApplication<KotlinJpaDemoApplication>(*args)
}
