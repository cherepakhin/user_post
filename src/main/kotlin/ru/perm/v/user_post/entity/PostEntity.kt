package ru.perm.v.user_post.entity


data class PostEntity(val id: Long, val title: String, val content: String, val author: UserEntity) {

}