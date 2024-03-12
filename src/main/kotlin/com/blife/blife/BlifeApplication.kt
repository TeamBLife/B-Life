package com.blife.blife

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.EnableAspectJAutoProxy

@SpringBootApplication
class BlifeApplication

fun main(args: Array<String>) {
	runApplication<BlifeApplication>(*args)
}
