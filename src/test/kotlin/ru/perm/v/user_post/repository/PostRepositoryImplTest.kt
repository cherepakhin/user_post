package ru.perm.v.user_post.repository

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class PostRepositoryImplTest {

    @Test
    fun getAll() {
        val postRepository = PostRepositoryImpl()
        assertEquals(2, postRepository.getAll().size)
    }
}