package ru.perm.v.user_post.dto

class PostDto(
    val id: Long,
    val title: String,
    val content: String,
    val author: UserDto
)

