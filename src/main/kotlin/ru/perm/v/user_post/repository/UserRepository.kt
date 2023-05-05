package ru.perm.v.userpost.repository

import ru.perm.v.userpost.entity.UserEntity

interface UserRepository {
    fun getById(id: Long): UserEntity
    fun getAll(): List<UserEntity>
    fun deleteById(id: Long)
    fun create(name: String, email: String): UserEntity
    fun update(id: Long, name: String, email: String): UserEntity
}
