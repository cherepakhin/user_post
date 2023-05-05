package ru.perm.v.user_post.mapper

import ru.perm.v.user_post.dto.PostDto
import ru.perm.v.user_post.entity.PostEntity
import ru.perm.v.user_post.dto.UserDto
import ru.perm.v.user_post.entity.UserEntity

class PostMapper() {
    fun toEntity(dto:PostDto): PostEntity {
        return PostEntity(dto.id, dto.title, dto.content, UserEntity(
            dto.author.id, dto.author.name, dto.author.email
            )
        )
    }
}