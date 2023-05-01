package ru.perm.v.user_post.service

import org.springframework.stereotype.Service
import ru.perm.v.user_post.entity.PostEntity
import ru.perm.v.user_post.repository.PostRepository

@Service
open class PostServiceImpl(val postRepository: PostRepository, val userService: UserService) : PostService {
    override fun getById(id: Long): PostEntity {
        return postRepository.getById(id)
    }

    override fun getAll(): List<PostEntity> {
        return postRepository.getAll()
// Оставлено для примера!
//        val dtos = posts.stream()
//            .map { p ->
//                PostDto(
//                    p.id,
//                    p.title,
//                    p.content,
//                    UserDto(p.author.id, p.author.name, p.author.email)
//                )
//            }
//            .toList()
//        return dtos
    }

    override fun deleteById(id: Long) {
        postRepository.deleteById(id)
    }

    override fun create(title: String, content: String, authorId: Long): PostEntity {
        val author = userService.getById(authorId)
        return postRepository.create(title, content, author)
    }
}