package ru.perm.v.user_post.mapper

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class UserMapperTest {

    @Test
    fun toEntityTest() {
        val entity = UserDto(1, "name", "email")
        //TODO
        assertEquals(UserDto(1, "name", "email"), UserMapper.toEntity(entity))
    }
}
