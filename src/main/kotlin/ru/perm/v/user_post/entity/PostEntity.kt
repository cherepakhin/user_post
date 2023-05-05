package ru.perm.v.userpost.entity

data class PostEntity(val id: Long, val title: String, val content: String, val author: UserEntity)
