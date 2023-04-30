package ru.perm.v.user_post.service

import org.springframework.stereotype.Service
import ru.perm.v.user_post.entity.UserEntity
import ru.perm.v.user_post.repository.UserRepository

@Service
open class UserServiceImpl(val userRepository: UserRepository) : UserService {


    override fun getById(id: Long): UserEntity {
        //TODO:  val existingUser = userRepository.findById(id)
        //          .orElseThrow { EntityNotFoundException("User not found with id $id") }
        val user = userRepository.getById(id)
        return user
    }

    override fun getAll(): List<UserEntity> {
        return userRepository.getAll()
//        return users.stream().map { u -> UserDto(u.id, u.name, u.email) }.toList()
    }

    override fun deleteById(id: Long) {
        userRepository.deleteById(id)
    }

    override fun create(id: Long, name: String, email: String): UserEntity {
        return userRepository.create(id, name, email)
//        return UserDto(user.id, user.name, user.email)
    }
}