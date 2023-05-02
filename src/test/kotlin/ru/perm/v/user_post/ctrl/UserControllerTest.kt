package ru.perm.v.user_post.ctrl

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.springframework.data.crossstore.ChangeSetPersister
import ru.perm.v.user_post.dto.UserDto
import ru.perm.v.user_post.entity.UserEntity
import ru.perm.v.user_post.exception.NotFoundEntityExcpt
import ru.perm.v.user_post.service.UserService

internal class UserControllerTest {

    private val mockUserService = mock<UserService> {
        on { getById(1) } doReturn UserEntity(1, "-", "-")
        on { getAll() } doReturn listOf(UserEntity(1, "-", "-"), UserEntity(2, "-", "-")) as List<UserEntity>
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

    @Test
    internal fun createUser() {
        val ID = 100L
        val NAME = "NAME"
        val EMAIL = "EMAIL"
        val userEntity = UserEntity(ID, NAME, EMAIL)
        val userDto = UserDto(userEntity)

        Mockito.`when`(mockUserService.getById(ID)).thenReturn(userEntity)
        Mockito.`when`(mockUserService.create(NAME, EMAIL)).thenReturn(userEntity)

        val createdUser = userController.createUser(userDto)

        assertEquals(ID, createdUser.id)
        assertEquals(NAME, createdUser.name)
        assertEquals(EMAIL, createdUser.email)
    }

    @Test
    fun deleteUser() {
        val ID = 100L
        userController.deleteUser(ID)
        verify(mockUserService, times(1)).deleteById(ID)
    }

    @Test
    fun updateNotExistUser() {
        val ID = 100L
        val NAME = "NAME"
        val EMAIL = "EMAIL"
        val user = UserDto(ID,NAME,EMAIL)
        val userEntityNotExist = UserEntity(-1, "-", "-")
        Mockito.`when`(mockUserService.getById(ID)).thenReturn(userEntityNotExist)

        assertThrows<NotFoundEntityExcpt> { userController.updateUser(user) }

        verify(mockUserService, times(1)).getById(ID)
    }


}