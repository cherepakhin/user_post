package ru.perm.v.userpost.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import ru.perm.v.userpost.entity.PostEntity
import ru.perm.v.userpost.entity.UserEntity
import ru.perm.v.userpost.repository.PostRepository

internal class PostServiceImplTest {

    val AUTHOR_ENTITY_1 = UserEntity(1, "NAME_1", "EMAIL_1")
    val AUTHOR_ENTITY_2 = UserEntity(2, "NAME_2", "EMAIL_2")
    private val mockPostRepository = mock<PostRepository> {
        on { getById(1) } doReturn PostEntity(1, "-", "-", AUTHOR_ENTITY_1)
        on { getAll() } doReturn listOf(
            PostEntity(1, "TITLE_1", "CONTENT_1", AUTHOR_ENTITY_1),
            PostEntity(2, "TITLE_2", "CONTENT_2", AUTHOR_ENTITY_2),
        )
    }
    val mockUserService = mock<UserService> {
        on { getById(1) } doReturn AUTHOR_ENTITY_1
    }
    val postService = PostServiceImpl(mockPostRepository, mockUserService)

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
        val POST_ID: Long = 1L
        postService.deleteById(POST_ID)

        verify(mockPostRepository, times(1)).deleteById(POST_ID)
    }

    @Test
    fun create() {
        val TITLE = "title"
        val CONTENT = "content"
        val AUTHOR_ID = 0L
        postService.create(TITLE, CONTENT, AUTHOR_ID)

        verify(mockUserService, times(1)).getById(AUTHOR_ID)
    }

    @Test
    fun update() {
        val ID = 100L
        val TITLE = "title"
        val CONTENT = "content"
        val AUTHOR_ID = 2L
        val USER_ENTITY = UserEntity(AUTHOR_ID, "NAME_2", "EMAIL_2")

        Mockito.doNothing().`when`(mockPostRepository).deleteById(ID)
        Mockito.`when`(mockPostRepository.create(TITLE, CONTENT, USER_ENTITY))
            .thenReturn(PostEntity(ID, TITLE, CONTENT, USER_ENTITY))

        postService.update(ID, TITLE, CONTENT, AUTHOR_ID)

        verify(mockPostRepository, times(1)).deleteById(ID)
        verify(mockUserService, times(1)).getById(AUTHOR_ID)
    }
}
