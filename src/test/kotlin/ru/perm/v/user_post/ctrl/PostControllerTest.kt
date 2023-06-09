package ru.perm.v.userpost.ctrl

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import ru.perm.v.userpost.dto.PostDto
import ru.perm.v.userpost.dto.UserDto
import ru.perm.v.userpost.entity.PostEntity
import ru.perm.v.userpost.entity.UserEntity
import ru.perm.v.userpost.service.PostService

internal class PostControllerTest {

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
        val posts = postController.getAll()
        assertEquals(2, posts.size)
    }

    // @Test
    // fun createPostMy() {
    //     val TITLE = "TITLE"
    //     val CONTENT = "CONTENT"
    //     val AUTHOR_DTO = UserDto(100, "NAME_100", "EMAIL_100")
    //     val postDto = PostDto(-1, TITLE, CONTENT, AUTHOR_DTO)
    //     val AUTHOR_ENTITY = UserEntity(100, "NAME_100", "EMAIL_100")

    //     Mockito.`when`(mockPostService.create(TITLE, CONTENT, AUTHOR_DTO.id))
    //         .thenReturn(PostEntity(0, TITLE, CONTENT, AUTHOR_ENTITY))

    //     val createdPost = postController.createPost(postDto)

    //     assertEquals(TITLE, createdPost.title)
    //     assertEquals(CONTENT, createdPost.content)
    //     assertEquals(100, createdPost.author.id)

    //     verify(mockPostService, times(1)).create(TITLE, CONTENT, AUTHOR_DTO.id)
    // }

    // @Test
    // fun updatePostTempExcpt() {
    //     val TITLE = "TITLE"
    //     val CONTENT = "CONTENT"
    //     val AUTHOR_DTO = UserDto(0, "-", "-")
    //     val postDto = PostDto(-1, TITLE, CONTENT, AUTHOR_DTO)
    //     assertThrows<NotReleasedExcpt>() {
    //         postController.updatePost(POST_ENTITY_100.id, postDto)
    //     }
    // }

    // @Test
    // fun getById() {
    //     val ID = 100L
    //     val TITLE = "TITLE"
    //     val CONTENT = "CONTENT"
    //     val AUTHOR_ENTITY = UserEntity(0, "-", "-")
    //     val POST_ENTITY = PostEntity(ID, TITLE, CONTENT, AUTHOR_ENTITY)

    //     Mockito.`when`(mockPostService.getById(100L))
    //         .thenReturn(POST_ENTITY)

    //     val post = postController.getById(100L)

    //     assertEquals(
    //         PostDto(
    //             ID,
    //             TITLE,
    //             CONTENT,
    //             UserDto(0L, "-", "-")),
    //         post)
    // }

    @Test
    fun `createPost should return a PostDto`() {
        // Arrange
        val postService = Mockito.mock(PostService::class.java)
        val postDto = PostDto(0, "title", "content", UserDto(1, "John", "john@example.com"))
        val postEntity = PostEntity(1, "title", "content", UserEntity(1, "John", "john@example.com"))
        `when`(postService.create("title", "content", 1)).thenReturn(postEntity)
        val controller = PostController(postService)

        // Act
        val result = controller.createPost(postDto)

        // Assert
        // assertEquals(postDto.title, result.title)
        // assertEquals(postDto.content, result.content)
        // assertEquals(postDto.author.id, result.author.id)
        // assertEquals(postDto.author.name, result.author.name)
        // assertEquals(postDto.author.email, result.author.email)
    }

// @Test
// fun `createPost should call postService#create`() {
//     val postService = mock(PostService::class.java)
//     val postDto = PostDto("title", "content", UserDto(1, "John", "john@example.com"))
//     val controller = PostController(postService)

//     controller.createPost(postDto)

//     verify(postService).create("title", "content", 1)
//     }
}
