package ru.perm.v.user_post.repository

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import ru.perm.v.user_post.entity.PostEntity
import ru.perm.v.user_post.entity.UserEntity

internal class PostRepositoryImplTest {

    @Test
    fun getAll() {
        val postRepository = PostRepositoryImpl()
        assertEquals(2, postRepository.getAll().size)
    }

    @Test
    fun getById() {
        val postRepository = PostRepositoryImpl()
        assertEquals(2L, postRepository.getById(2L).id)
    }

    @Test
    fun getByIdNotExist() {
        val postRepository = PostRepositoryImpl()
        assertEquals(-1L, postRepository.getById(100L).id)
    }

    @Test
    fun deleteById() {
        val postRepository = PostRepositoryImpl()
        val size = postRepository.getAll().size
        val DELETED_POST_ID = 2L
        postRepository.deleteById(DELETED_POST_ID)
        assertEquals(size - 1, postRepository.getAll().size)

        // Проверка, что по DELETED_POST_ID возвращается пустой пост
        val userEmpty = UserEntity(-1, "-", "-")
        assertEquals(PostEntity(-1, "-", "-", userEmpty), postRepository.getById(DELETED_POST_ID))
    }

    @Test
    fun create() {
        val postRepository = PostRepositoryImpl()
        val sizeBefore = postRepository.getAll()
        val ID = 101L
        val CONTENT = "NAME_101"
        val TITLE = "TITLE_101"
        val AUTHOR = UserEntity(1, "AUTHOR_NAME", "AUTHOR_EMAIL")
        val post = postRepository.create(ID, TITLE, CONTENT, AUTHOR)
        assertEquals(PostEntity(ID, TITLE, CONTENT, AUTHOR), post)
    }
}