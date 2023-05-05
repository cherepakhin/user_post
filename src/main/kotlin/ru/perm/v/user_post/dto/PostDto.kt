package ru.perm.v.userpost.dto

data class PostDto(
    val id: Long,
    val title: String,
    val content: String,
    val author: UserDto,
)
