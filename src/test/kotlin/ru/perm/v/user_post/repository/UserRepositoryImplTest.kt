package ru.perm.v.user_post.repository

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.perm.v.user_post.entity.UserEntity

internal class UserRepositoryImplTest {

    @Test
    fun getById() {
        val userRepository = UserRepositoryImpl()
        val user = userRepository.getById(1)

        assertEquals(UserEntity(1, "name1", "email1"), user)
    }

    @Test
    fun getByIdIfNotFound() {
        val userRepository = UserRepositoryImpl()
        val user = userRepository.getById(100)

        assertEquals(UserEntity(-1, "not found", "not found"), user)
    }

    @Test
    fun getAll() {
        val userRepository = UserRepositoryImpl()
        val users = userRepository.getAll()

        assertEquals(2, users.size)
    }

    @Test
    fun deleteById() {
        val userRepository = UserRepositoryImpl()
        val beforeSize = userRepository.getAll().size
        userRepository.deleteById(1)
        val sizeAfterDelete = userRepository.getAll().size
        assertEquals(1, beforeSize - sizeAfterDelete)
    }

    @Test
    fun createUser() {
        val userRepository = UserRepositoryImpl()
        val user = userRepository.create(100, "NAME", "EMAIL")
        assertEquals(UserEntity(100, "NAME", "EMAIL"), user)
    }
}