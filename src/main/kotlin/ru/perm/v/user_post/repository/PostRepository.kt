package ru.perm.v.user_post.repository

import ru.perm.v.user_post.entity.PostEntity
import ru.perm.v.user_post.entity.UserEntity

//@Repository
//interface PostRepository: CrudRepository<Post, Long> {
interface PostRepository {
    fun getById(id: Long): PostEntity;
    fun getAll(): List<PostEntity>
    fun deleteById(id: Long)
    fun create(id: Long, title: String, content: String, author: UserEntity): PostEntity
}