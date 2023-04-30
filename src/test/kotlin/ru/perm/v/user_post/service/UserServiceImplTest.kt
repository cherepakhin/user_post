package ru.perm.v.user_post.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import ru.perm.v.user_post.entity.UserEntity
import ru.perm.v.user_post.repository.UserRepository

internal class UserServiceImplTest {
    private val mockUserRepository = mock<UserRepository> {
        on { getById(1) } doReturn UserEntity(1, "-", "-")
        on { getAll() } doReturn listOf(UserEntity(1, "-", "-"), UserEntity(2, "-", "-"))
        on { create(1, "NAME", "EMAIL") } doReturn UserEntity(1, "NAME", "EMAIL")
    }
    private val userService = UserServiceImpl(mockUserRepository)

    @Test
    fun getById() {
        val ID: Long = 1
        val user = userService.getById(ID)
        assertEquals(ID, user.id)
    }

    @Test
    internal fun getAll() {
        val users = userService.getAll()
        assertEquals(2, users.size)
    }

    @Test
    fun create() {
        val user = userService.create(1, "NAME", "EMAIL")
        assertEquals(UserEntity(1, "NAME", "EMAIL"), user)
    }

    @Test
    fun deleteById() {
        userService.deleteById(1)
        verify(mockUserRepository, times(1)).deleteById(1)
    }
}