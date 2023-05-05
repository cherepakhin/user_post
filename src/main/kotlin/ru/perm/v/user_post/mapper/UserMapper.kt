package ru.perm.v.userpost.mapper

import ru.perm.v.userpost.dto.UserDto
import ru.perm.v.userpost.entity.UserEntity

class UserMapper() {
    fun toEntity(dto: UserDto): UserEntity {
        return UserEntity(dto.id, dto.name, dto.email)
    }

    fun toDto(entity: UserEntity): UserDto {
        return UserDto(entity.id, entity.name, entity.email)
    }
}
