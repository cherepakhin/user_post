package ru.perm.v.userpost.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.`when`
import org.mockito.kotlin.*
import ru.perm.v.userpost.entity.UserEntity
import ru.perm.v.userpost.exception.NotFoundEntityExcpt
import ru.perm.v.userpost.repository.UserRepository

internal class UserServiceImplTest {
    private val mockUserRepository = mock<UserRepository> {
        on { getById(1) } doReturn UserEntity(1, "-", "-")
        on { getAll() } doReturn listOf(UserEntity(1, "-", "-"), UserEntity(2, "-", "-"))
        on { create("NAME", "EMAIL") } doReturn UserEntity(1, "NAME", "EMAIL")
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
        val user = userService.create("NAME", "EMAIL")
        assertEquals(UserEntity(1, "NAME", "EMAIL"), user)
    }

    @Test
    fun deleteById() {
        userService.deleteById(1)
        verify(mockUserRepository, times(1)).deleteById(1)
    }

    @Test
    internal fun save() {
        val ID: Long = 100
        val NAME: String = "NAME"
        val EMAIL: String = "EMAIL"

        doReturn(UserEntity(ID, NAME, EMAIL)).`when`(mockUserRepository).getById(ID)
        doReturn(UserEntity(ID, NAME, EMAIL)).`when`(mockUserRepository).update(ID, NAME, EMAIL)

        val savedUser = userService.save(UserEntity(ID, NAME, EMAIL))

        assertEquals(UserEntity(ID, NAME, EMAIL), savedUser)
        verify(mockUserRepository, times(1)).getById(ID)
        verify(mockUserRepository, times(1)).update(ID, NAME, EMAIL)
    }

    @Test
    internal fun checkUserNotFoundExcpt() {
        val ID: Long = 100
        val NAME: String = "NAME"
        val EMAIL: String = "EMAIL"

        `when`(mockUserRepository.getById(ID)).thenReturn(UserEntity(-1))

        assertThrows<NotFoundEntityExcpt> { userService.save(UserEntity(ID, NAME, EMAIL)) }
    }
}
