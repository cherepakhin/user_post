package ru.perm.v.userpost.service

import org.springframework.stereotype.Service
import ru.perm.v.userpost.entity.PostEntity
import ru.perm.v.userpost.repository.PostRepository

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

    override fun update(id: Long, title: String, content: String, authorId: Long): PostEntity {
        postRepository.deleteById(id)
        val authorEntity = userService.getById(authorId)
        return postRepository.create(title, content, authorEntity)
    }
}
