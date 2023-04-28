package ru.perm.v.user_post.repository

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class PostRepositoryImplTest {

    @Test
    fun getAll() {
        val postRepository = PostRepositoryImpl()
        assertEquals(2, postRepository.getAll().size)
    }

    @Test
    fun getById() {
        val postRepository = PostRepositoryImpl()
        assertEquals(2L, postRepository.getById(2L).id)
    }

    @Test
    fun getByIdNotExist() {
        val postRepository = PostRepositoryImpl()
        assertEquals(-1L, postRepository.getById(100L).id)
    }
}