package ru.perm.v.userpost.service

import ru.perm.v.userpost.entity.UserEntity

interface UserService {
    fun getById(id: Long): UserEntity
    fun getAll(): List<UserEntity>
    fun deleteById(id: Long)
    fun create(name: String, email: String): UserEntity
    fun save(user: UserEntity): UserEntity
}
