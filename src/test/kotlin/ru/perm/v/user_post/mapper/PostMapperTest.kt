package ru.perm.v.userpost.mapper

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.perm.v.userpost.dto.UserDto
import ru.perm.v.userpost.entity.UserEntity

internal class PostMapperTest {

    @Test
    fun toEntityTest() {
        val dto = UserDto(1, "name", "email")
        val mapper = UserMapper()
        assertEquals(UserEntity(1, "name", "email"), mapper.toEntity(dto))
    }
}
