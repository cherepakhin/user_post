package ru.perm.v.userpost.repository

import ru.perm.v.userpost.entity.PostEntity
import ru.perm.v.userpost.entity.UserEntity

// Закомментировано @Repository. Репозиторий реализую сам.
// @Repository
// interface PostRepository: CrudRepository<Post, Long> {
interface PostRepository {
    fun getById(id: Long): PostEntity
    fun getAll(): List<PostEntity>
    fun deleteById(id: Long)
    fun create(title: String, content: String, author: UserEntity): PostEntity
}
