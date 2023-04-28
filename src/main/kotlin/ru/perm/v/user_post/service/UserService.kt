package ru.perm.v.user_post.service

import ru.perm.v.user_post.dto.UserDto

interface UserService {
    fun getById(id: Long): UserDto;
    fun getAll(): List<UserDto>;
    fun deleteById(id: Long);
    fun create(id:Long, name:String, email:String): UserDto;
//    fun update(id:Long, name:String, email:String): UserDto;
}