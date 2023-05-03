package ru.perm.v.user_post.ctrl

import org.springframework.web.bind.annotation.*
import ru.perm.v.user_post.dto.PostDto
import ru.perm.v.user_post.dto.UserDto
import ru.perm.v.user_post.entity.PostEntity
import ru.perm.v.user_post.exception.NotFoundEntityExcpt
import ru.perm.v.user_post.exception.NotReleasedExcpt
import ru.perm.v.user_post.service.PostService
import java.lang.String.format

/**
 * Rest-контроллер постов.
 *
 * Замечание: Контроллер обращается напрямую к слою Service и выполняет не только конвертацию в DTO,
 * но и некоторые внутренние операции, ктр. д.б. в отдельном промежуточном слое. Сделано для простоты.
 */
@RestController
@RequestMapping("/posts")
class PostController(private val postService: PostService) {

    @GetMapping("/")
    fun getAll(): List<PostDto> {
        return postService.getAll().stream().map {
            PostDto(it.id, it.title, it.content, UserDto(it.author.id, it.author.name, it.author.email))
        }.toList()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): PostDto {
        val post = postService.getById(id)
        return PostDto(post.id, post.title, post.content, UserDto(post.author.id, post.author.name, post.author.email))
    }

    /**
     * Создание сообщения
     */
    @PutMapping("/")
    fun createPost(@RequestBody postDto: PostDto): PostDto {
        var post = postService.create(postDto.title, postDto.content, postDto.author.id)
        return PostDto(
            post.id,
            post.title,
            post.content,
            UserDto(post.author.id, post.author.name, post.author.email)
        )
    }

    /**
     * Обновление сообщения
     */
    @PostMapping("/")
    fun updatePost(@RequestBody postDto: PostDto): PostDto {
        val existPost = postService.getById(postDto.id)
        if(existPost.id.equals(-1L)) {
            throw NotFoundEntityExcpt(format("Post with id %s not found", existPost.id))
        }
        return PostDto(existPost.id, existPost.title,existPost.content, postDto.author)
//        val updatedPost=postService.update(existPost.id,postDto.title,postDto.content, postDto.author.id)
//        return PostDto(updatedPost.id, updatedPost.title, updatedPost.content,UserDto(updatedPost.author))
    }


    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable id: Long) {
//        postRepository.deleteById(id)
    }
}
