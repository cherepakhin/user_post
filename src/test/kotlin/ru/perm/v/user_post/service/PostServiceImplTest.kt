package ru.perm.v.user_post.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import ru.perm.v.user_post.entity.PostEntity
import ru.perm.v.user_post.entity.UserEntity
import ru.perm.v.user_post.repository.PostRepository

internal class PostServiceImplTest {

    val AUTHOR_ENTITY_1 = UserEntity(1, "NAME_1", "EMAIL_1")
    val AUTHOR_ENTITY_2 = UserEntity(2, "NAME_2", "EMAIL_2")
    private val mockPostRepository = mock<PostRepository> {
        on { getById(1) } doReturn PostEntity(1, "-", "-", AUTHOR_ENTITY_1)
        on { getAll() } doReturn listOf(
            PostEntity(1, "TITLE_1", "CONTENT_1", AUTHOR_ENTITY_1),
            PostEntity(2, "TITLE_2", "CONTENT_2", AUTHOR_ENTITY_2)
        )
    }
    val postService = PostServiceImpl(mockPostRepository)

    @Test
    fun getById() {
        val post = postService.getById(1)
        assertEquals(1, post.id)
    }

    @Test
    fun getAll() {
        val posts = postService.getAll()
        assertEquals(2, posts.size)
        assertEquals(AUTHOR_ENTITY_1.id, posts.get(0).author.id)
        assertEquals(AUTHOR_ENTITY_2.id, posts.get(1).author.id)
    }

    @Test
    internal fun deleteById() {
        val POST_ID:Long = 1L
        postService.deleteById(POST_ID)

        verify(mockPostRepository, times(1) ).deleteById(POST_ID)
    }
}