package ru.perm.v.user_post.repository

import org.springframework.stereotype.Component
import ru.perm.v.user_post.entity.PostEntity
import ru.perm.v.user_post.entity.UserEntity
import java.util.Comparator

//TODO: Имплементация вместо Spring Repository
//@Repository
@Component
class PostRepositoryImpl : PostRepository {
    val userEmpty = UserEntity(-1, "-", "-")
    val emptyPost = PostEntity(-1, "-", "-", userEmpty)

    val posts = mutableListOf<PostEntity>(
        PostEntity(
            1, "TITLE_1", "CONTENT_1",
            UserEntity(1, "NAME_1", "EMAIL_1")
        ),
        PostEntity(
            2, "TITLE_2", "CONTENT_2",
            UserEntity(2, "NAME_2", "EMAIL_2")
        )
    )

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

    override fun create(title: String, content: String, author: UserEntity): PostEntity {
        val maxId = posts.stream().max(Comparator.comparing(PostEntity::id)).get().id
        val newPost = PostEntity(maxId + 1, title, content, author)
        posts.add(newPost)
        return newPost
    }

//    override fun create(title: String, content: String, authorId: Long): PostEntity {
//        TODO("Not yet implemented")
//    }

//    override fun create(title: String, content: String, author: UserEntity): PostEntity {
//        val author = UserEntity(authorId, "-","-")
//        val author = getAll().first() { a -> a.id == authorId }
//        val newPost = PostEntity(0, title, content, author)
//        posts.add(newPost)
//        return newPost
//    }

}