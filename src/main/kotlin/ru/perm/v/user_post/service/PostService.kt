package ru.perm.v.user_post.service

import org.springframework.stereotype.Service
import ru.perm.v.user_post.dto.PostDto

@Service
interface PostService {
    fun getById(id: Long): PostDto;
    fun getAll(): List<PostDto>;
    fun deleteById(id: Long);
    fun create(id: Long, name: String, email: String): PostDto;
}