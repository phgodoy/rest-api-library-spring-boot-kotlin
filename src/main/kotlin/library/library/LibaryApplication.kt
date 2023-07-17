package library.library

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LibaryApplication

fun main(args: Array<String>) {
	runApplication<LibaryApplication>(*args)
}
