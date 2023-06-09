package ru.perm.v.userpost.repository

import org.springframework.stereotype.Component
import ru.perm.v.userpost.entity.PostEntity
import ru.perm.v.userpost.entity.UserEntity

// Имплементация вместо Spring Repository. Поэтому, закомментировано @Repository
// и обозначен просто как @Component
// @Repository
@Component
class PostRepositoryImpl : PostRepository {
    private val userEmpty = UserEntity(-1L, "-", "-")
    private val emptyPost = PostEntity(-1L, "-", "-", userEmpty)

    private val posts = mutableListOf<PostEntity>(
        PostEntity(
            1L,
            "TITLE_1",
            "CONTENT_1",
            UserEntity(1, "NAME_1", "EMAIL_1"),
        ),
        PostEntity(
            2L,
            "TITLE_2",
            "CONTENT_2",
            UserEntity(2, "NAME_2", "EMAIL_2"),
        ),
    )

    override fun getById(id: Long): PostEntity {
        // Можно заменить на NotFoundException
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
}
