package ru.perm.v.user_post.ctrl

import org.springframework.web.bind.annotation.*
import ru.perm.v.user_post.ctrl.exception.NotReleasedExcpt
import ru.perm.v.user_post.dto.PostDto
import ru.perm.v.user_post.dto.UserDto
import ru.perm.v.user_post.service.PostService

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
    fun updatePost(@PathVariable id: Long, @RequestBody postDto: PostDto): PostDto {
//        val existingPost =
//            postRepository.findById(id).orElseThrow { EntityNotFoundException("Post not found with id $id") }
//        existingPost.title = postDto.title
//        existingPost.content = postDto.content
//        existingPost.author = User(postDto.author.id, postDto.author.name, postDto.author.email)
//        return PostDto(
//            postRepository.save(existingPost).id,
//            existingPost.title,
//            existingPost.content,
//            UserDto(existingPost.author.id, existingPost.author.name, existingPost.author.email)
//        )
        throw NotReleasedExcpt()
    }


    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable id: Long) {
//        postRepository.deleteById(id)
    }
}
