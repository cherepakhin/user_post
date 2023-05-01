package ru.perm.v.user_post.repository

import ru.perm.v.user_post.entity.PostEntity
import ru.perm.v.user_post.entity.UserEntity

// Закомментировано @Repository. Репозиторий реализую сам.
//@Repository
//interface PostRepository: CrudRepository<Post, Long> {
interface PostRepository {
    fun getById(id: Long): PostEntity;
    fun getAll(): List<PostEntity>
    fun deleteById(id: Long)
    fun create(title: String, content: String, author: UserEntity): PostEntity
}