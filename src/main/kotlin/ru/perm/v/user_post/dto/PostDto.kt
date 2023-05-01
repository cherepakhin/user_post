package ru.perm.v.user_post.dto

data class PostDto(
    val id: Long,
    val title: String,
    val content: String,
    val author: UserDto
)

