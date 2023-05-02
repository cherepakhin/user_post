package ru.perm.v.user_post.service

import org.springframework.stereotype.Service
import ru.perm.v.user_post.entity.UserEntity
import ru.perm.v.user_post.exception.NotFoundEntityExcpt
import ru.perm.v.user_post.repository.UserRepository

@Service
open class UserServiceImpl(val userRepository: UserRepository) : UserService {


    override fun getById(id: Long): UserEntity {
        return userRepository.getById(id)
    }

    override fun getAll(): List<UserEntity> {
        return userRepository.getAll()
    }

    override fun deleteById(id: Long) {
        userRepository.deleteById(id)
    }

    override fun create(name: String, email: String): UserEntity {
        return userRepository.create(name, email)
    }

    override fun save(userEntity: UserEntity): UserEntity {
        val user = getById(userEntity.id)
        if (user.id.equals(-1L)) {
            throw NotFoundEntityExcpt(String.format("Not found user with " + userEntity.id))
        }
        val savedUser = userRepository.update(user.id, user.name, user.email)
        return savedUser
    }
}