package ru.perm.v.user_post.mapper

import ru.perm.v.user_post.dto.UserDto
import ru.perm.v.user_post.entity.UserEntity

class UserMapper() {
    fun toEntity(dto: UserDto):UserEntity {
        return UserEntity(dto.id, dto.name, dto.email)
    }

    fun toDto(entity:UserEntity): UserDto {
        return UserDto(entity.id, entity.name, entity.email)
    }
}