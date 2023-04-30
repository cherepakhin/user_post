package ru.perm.v.user_post.ctrl

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import ru.perm.v.user_post.dto.UserDto
import ru.perm.v.user_post.entity.PostEntity
import ru.perm.v.user_post.entity.UserEntity
import ru.perm.v.user_post.service.PostService

internal class PostControllerTest {

    val AUTOR_DTO_100 = UserDto(100, "USER_100", "EMAIL_100")
    val AUTOR_DTO_200 = UserDto(200, "USER_200", "EMAIL_200")

//    val POST_DTO_1 = PostDto(1, "TITLE_1", "CONTENT_1", AUTOR_DTO_100)
//    val POST_DTO_2 = PostDto(2, "TITLE_2", "CONTENT_2", AUTOR_DTO_200)

    val AUTHOR_ENTITY_100 = UserEntity(100, "NAME_100", "EMAIL_100")
    val AUTHOR_ENTITY_200 = UserEntity(200, "NAME_200", "EMAIL_200")

    val POST_ENTITY_100 = PostEntity(100, "TITLE_ENTITY_100", "CONTENT_ENTITY_100", AUTHOR_ENTITY_100)
    val POST_ENTITY_200 = PostEntity(200, "TITLE_ENTITY_200", "CONTENT_ENTITY_200", AUTHOR_ENTITY_200)

    private val mockPostService = mock<PostService> {
        on { getById(100) } doReturn POST_ENTITY_100
        on { getAll() } doReturn listOf(POST_ENTITY_100, POST_ENTITY_200)
    }
    private val postController = PostController(mockPostService)

    @Test
    fun getPosts() {
        val posts = postController.getPosts()
        assertEquals(2, posts.size)
    }
}