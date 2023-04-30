package ru.perm.v.user_post.service

import ru.perm.v.user_post.entity.UserEntity

interface UserService {
    fun getById(id: Long): UserEntity;
    fun getAll(): List<UserEntity>;
    fun deleteById(id: Long);
    fun create(id:Long, name:String, email:String): UserEntity;
}