package ru.perm.v.userpost.service

import org.springframework.stereotype.Service
import ru.perm.v.userpost.entity.PostEntity

@Service
interface PostService {
    fun getById(id: Long): PostEntity
    fun getAll(): List<PostEntity>
    fun deleteById(id: Long)
    fun create(title: String, content: String, authorId: Long): PostEntity
    fun update(id: Long, title: String, content: String, authorId: Long): PostEntity
}
