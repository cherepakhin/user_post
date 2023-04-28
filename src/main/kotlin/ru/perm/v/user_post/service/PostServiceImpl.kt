package ru.perm.v.user_post.service

import org.springframework.stereotype.Service
import ru.perm.v.user_post.dto.PostDto
import ru.perm.v.user_post.dto.UserDto
import ru.perm.v.user_post.repository.PostRepository

@Service
class PostServiceImpl(val postRepository: PostRepository) : PostService {
    override fun getById(id: Long): PostDto {
        val post = postRepository.getById(id)
        val authorDto = UserDto(post.author.id, post.author.name, post.author.email)
        return PostDto(post.id, post.title, post.content, authorDto)
    }

    override fun getAll(): List<PostDto> {
        val posts = postRepository.getAll()
        val dtos = posts.stream()
            .map { p ->
                PostDto(
                    p.id,
                    p.title,
                    p.content,
                    UserDto(p.author.id, p.author.name, p.author.email)
                )
            }
            .toList()
        return dtos
    }

    override fun deleteById(id: Long) {
        TODO("Not yet implemented")
    }

    override fun create(id: Long, name: String, email: String): PostDto {
        TODO("Not yet implemented")
    }
}