package ru.perm.v.user_post.service

import org.springframework.stereotype.Service
import ru.perm.v.user_post.entity.PostEntity

@Service
interface PostService {
    fun getById(id: Long): PostEntity;
    fun getAll(): List<PostEntity>;
    fun deleteById(id: Long);
    fun create(title: String, content: String, authorId: Long): PostEntity;
}