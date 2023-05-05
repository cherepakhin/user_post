package ru.perm.v.userpost.mapper

import ru.perm.v.userpost.dto.PostDto
import ru.perm.v.userpost.entity.PostEntity
import ru.perm.v.userpost.entity.UserEntity

class PostMapper() {
    fun toEntity(dto: PostDto): PostEntity {
        return PostEntity(
            dto.id,
            dto.title,
            dto.content,
            UserEntity(
                dto.author.id,
                dto.author.name,
                dto.author.email,
            ),
        )
    }
}
