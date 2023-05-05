package ru.perm.v.userpost.dto

import ru.perm.v.userpost.entity.UserEntity

data class UserDto(
    val id: Long,
    val name: String,
    val email: String,
) {
    constructor(user: UserEntity) : this(user.id, user.name, user.email)
}
