package ru.perm.v.user_post.ctrl

import org.springframework.web.bind.annotation.*
import ru.perm.v.user_post.dto.PostDto
import ru.perm.v.user_post.dto.UserDto
import ru.perm.v.user_post.exception.NotFoundEntityExcpt
import ru.perm.v.user_post.exception.NotReleasedExcpt
import ru.perm.v.user_post.entity.PostEntity
import ru.perm.v.user_post.entity.UserEntity

import ru.perm.v.user_post.service.PostService
import com.github.benmanes.caffeine.cache.Caffeine
import java.util.concurrent.TimeUnit


/**
 * Rest-контроллер постов.
 *
 * Замечание: Контроллер обращается напрямую к слою Service и выполняет не только конвертацию в DTO,
 * но и некоторые внутренние операции, ктр. д.б. в отдельном промежуточном слое. Сделано для простоты.
 */
@RestController
@RequestMapping("/posts")
class PostController(private val postService: PostService) {

    // private fun PostDto.toPostEntity(): PostEntity {
    //     return PostEntity(
    //         id = id!!,
    //         title = title,
    //         content = content,
    //         author = UserEntity(
    //             id = author.id,
    //             name = author.name,
    //             email = author.email
    //         )
    //     )
    // }

    // private fun PostEntity.toDto(): PostDto {
    //     return PostDto(
    //         id = id!!,
    //         title = title,
    //         content = content,
    //         author = UserDto(
    //             id = author.id,
    //             name = author.name,
    //             email = author.email
    //         )
    //     )
    // }
    // Create a cache for posts
    val postCache = Caffeine.newBuilder()
        .expireAfterWrite(10, TimeUnit.MINUTES) // expire entries after 10 minutes
        .maximumSize(1000) // limit the cache size to 1000 entries
        .build<Long, PostDto>()

    @GetMapping("/")
    fun getAll(): List<PostDto> {
        return postService.getAll().stream().map {
            PostDto(it.id, it.title, it.content, UserDto(it.author.id, it.author.name, it.author.email))
        }.toList()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): PostDto {
        // Try to get the post from the cache
        // val post = postCache.getIfPresent(id)

        // if (post != null) {
        //     return post.toDto()
        // }

        // If the post is not in the cache, get it from the service
        // val fetchedPost = postService.get(id)

        // Add the fetched post to the cache
        // postCache.put(id, fetchedPost)
        val userDto = UserDto(-1, "-","-")
        return PostDto(0,"-","-", userDto)
    }

    /**
     * Создание сообщения
     */
    @PutMapping("/")
    fun createPost(@RequestBody postDto: PostDto): PostDto {
        // val post = postService.create(postDto.toPost())

        // Add the new post to the cache
        // postCache.put(post.id!!, PostDto(post.id, post.title, post.content,  ))

        return PostDto(0, "", "", UserDto(0,"-","-"))
    }

    /**
     * Обновление сообщения
     */
    @PostMapping("/")
    fun updatePost(@PathVariable id: Long, @RequestBody postDto: PostDto): PostDto {
        val existPost = postService.getById(postDto.id)
        if(existPost.id.equals(-1L)) {
            // throw NotFoundEntityExcpt(format("Post with id %s not found", existPost.id))
        }
        return PostDto(existPost.id, existPost.title,existPost.content, postDto.author)
    }


    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable id: Long) {
//        postRepository.deleteById(id)
    }
}
