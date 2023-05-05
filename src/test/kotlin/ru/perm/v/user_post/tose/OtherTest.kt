package ru.perm.v.userpost.tose

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertLinesMatch
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class OtherTest {
    @Test
    internal fun simple() {
        val b = true
        Assertions.assertEquals(true, b)
    }

    @Test
    @DisplayName("Test list") // такое имя теста будет в результатах
    internal fun `Test equals list`() {
        val list = setOf<String>("3", "2", "1")
        val newList = list.map { it -> it + "0" }

        assertEquals(listOf("30", "20", "10"), newList)

        assertLinesMatch(listOf("30", "20", "10"), newList)

        assertLinesMatch(listOf("30", "20", "10"), newList)
    }
}
