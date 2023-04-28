package ru.perm.v.user_post.ctrl

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import ru.perm.v.user_post.dto.UserDto
import ru.perm.v.user_post.entity.UserEntity
import ru.perm.v.user_post.service.UserService

internal class UserControllerTest {

    private val mockUserService = mock<UserService> {
        on { getById(1) } doReturn UserDto(1, "-", "-")
        on { getAll() } doReturn listOf(UserDto(1, "-", "-"), UserEntity(2, "-", "-")) as List<UserDto>
    }
    private val userController = UserController(mockUserService)

    @Test
    internal fun getUsers() {
        val users = userController.getUsers()
        assertEquals(2, users.size)
    }

    @Test
    internal fun getById() {
        val user = userController.getUserById(1)
        assertEquals(UserDto(1, "-", "-"), user)
    }
}