package ru.perm.v.user_post.repository

import org.springframework.stereotype.Component
import ru.perm.v.user_post.entity.UserEntity

//Временная имплементация вместо Spring Repository
//@Repository
@Component
class UserRepositoryImpl : UserRepository {
    private val userEmpty = UserEntity(-1L, "not found", "not found")
    private val users = mutableListOf(
        UserEntity(1L, "name1", "email1"),
        UserEntity(2L, "name2", "email2"),
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
        var user = getById(id)
        if (!user.id.equals(userEmpty.id)) {
            users.remove(user)
        }
    }

    override fun create(name: String, email: String): UserEntity {
        val id: Long = users.stream().mapToLong() { u -> u.id }.max().orElse(-1)
        val userEntity = UserEntity(id + 1, name, email)
        users.plus(userEntity)
        return userEntity
    }

    override fun update(id: Long, name: String, email: String): UserEntity {
        var user = getById(id)
        if (user != null) {
            deleteById(id)
        }
        return create(name, email)
    }
}