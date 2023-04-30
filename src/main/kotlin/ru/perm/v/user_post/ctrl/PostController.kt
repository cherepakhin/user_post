package ru.perm.v.user_post.ctrl

import org.springframework.web.bind.annotation.*
import ru.perm.v.user_post.dto.PostDto
import ru.perm.v.user_post.dto.UserDto
import ru.perm.v.user_post.entity.PostEntity
import ru.perm.v.user_post.entity.UserEntity
import ru.perm.v.user_post.service.PostService

@RestController
@RequestMapping("/posts")
class PostController(private val postService: PostService) {

    @GetMapping("/")
    fun getPosts(): List<PostDto> {
        return postService.getAll().stream().map {
            PostDto(it.id, it.title, it.content, UserDto(it.author.id, it.author.name, it.author.email))
        }.toList()
    }

    @GetMapping("/{id}")
    fun getPostById(@PathVariable id: Long): PostDto {
        val post = postService.getById(id)
        return PostDto(post.id, post.title, post.content, UserDto(post.author.id, post.author.name, post.author.email))
    }

    @PostMapping("/")
    fun createPost(@RequestBody postDto: PostDto): PostDto {
        val post = PostEntity(
            postDto.id,
            postDto.title,
            postDto.content,
            UserEntity(postDto.author.id, postDto.author.name, postDto.author.email),
        )
//        val savedPost = postRepository.save(post)
        return PostDto(
            post.id,
            post.title,
            post.content,
            UserDto(post.author.id, post.author.name, post.author.email)
        )
    }

//    @PutMapping("/{id}")
//    fun updatePost(@PathVariable id: Long, @RequestBody postDto: PostDto): PostDto {
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
//    }

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable id: Long) {
//        postRepository.deleteById(id)
    }
}
