package ru.perm.v.user_post.mapper

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.perm.v.user_post.mapper.UserMapper
import ru.perm.v.user_post.dto.UserDto
import ru.perm.v.user_post.entity.UserEntity

internal class UserMapperTest {

    @Test
    fun toEntityTest() {
        val dto = UserDto(1, "name", "email")
        val mapper = UserMapper()
        assertEquals(UserEntity(1, "name", "email"), mapper.toEntity(dto))
    }
}
