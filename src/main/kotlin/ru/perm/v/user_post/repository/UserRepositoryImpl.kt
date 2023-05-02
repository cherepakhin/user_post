package ru.perm.v.user_post.repository

import org.springframework.stereotype.Component
import ru.perm.v.user_post.entity.UserEntity

//Временная имплементация вместо Spring Repository
//@Repository
@Component
class UserRepositoryImpl : UserRepository {
    private val userEmpty = UserEntity(-1, "not found", "not found")
    private val users = mutableListOf(
        UserEntity(1, "name1", "email1"),
        UserEntity(2, "name2", "email2"),
    )

    override fun getById(id: Long): UserEntity {
        val user = users.filter { it.id.equals(id) }.firstOrNull()
        // Если user не найден - вернуть "пустышку"
        return user ?: userEmpty
    }

    override fun getAll(): List<UserEntity> {
        return users
    }

    override fun deleteById(id: Long) {
        var user=getById(id)
        if( !user.id.equals(userEmpty.id)) {
            users.remove(user)
        }
    }

    override fun create(id: Long, name: String, email: String): UserEntity {
        var userEntity = getById(id)
        if (userEntity != null) {
            deleteById(id)
        }
        userEntity = UserEntity(id, name, email)
        users.plus(userEntity)
        return userEntity
    }

    override fun update(id: Long, name: String, email: String): UserEntity {
        var user = getById(id)
        if (user != null) {
            deleteById(id)
        }
        return create(id, name, email)
    }
}