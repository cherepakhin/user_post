package ru.perm.v.user_post.repository

import org.springframework.stereotype.Component
import ru.perm.v.user_post.entity.PostEntity
import ru.perm.v.user_post.entity.UserEntity

//TODO: Временная имплементация вместо Spring Repository
//@Repository
@Component
class PostRepositoryImpl : PostRepository {
    val userEmpty = UserEntity(-1, "-", "-")
    val user1 = UserEntity(1, "NAME_1", "EMAIL_1")
    val user2 = UserEntity(2, "NAME_2", "EMAIL_2")
    val emptyPost = PostEntity(-1, "-", "-", userEmpty)
    val post1 = PostEntity(1, "TITLE_1", "CONTENT_1", user1)
    val post2 = PostEntity(2, "TITLE_2", "CONTENT_2", user2)

    val posts = mutableListOf<PostEntity>(post1, post2)

    override fun getById(id: Long): PostEntity {
        return posts.filter { it.id.equals(id) }.getOrElse(0) { emptyPost }
    }

    override fun getAll(): List<PostEntity> {
        return posts
    }

    override fun deleteById(id: Long) {
        val post = getById(id)
        if (!post.equals(emptyPost)) posts.remove(post)
    }

    override fun create(id: Long, title: String, content: String, author: UserEntity): PostEntity {
        return PostEntity(id, title, content, author)
    }

}