package ru.perm.v.user_post

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class UserPostApplication

fun main(args: Array<String>) {
    runApplication<UserPostApplication>(*args)
}
