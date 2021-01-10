package dev.gesiel.springweb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringwebApplication

fun main(args: Array<String>) {
	runApplication<SpringwebApplication>(*args)
}
