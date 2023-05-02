package ru.perm.v.user_post.repository

import ru.perm.v.user_post.entity.UserEntity

interface UserRepository {
    fun getById(id: Long): UserEntity;
    fun getAll(): List<UserEntity>
    fun deleteById(id: Long)
    fun create(name: String, email: String): UserEntity
    fun update(id: Long, name: String, email: String): UserEntity
}