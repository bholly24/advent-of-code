package aoc2025.utils

import org.junit.jupiter.api.Assertions.assertEquals

fun <T> logAndAssertEquals(expected: T, actual: () -> T) {
    val start = System.nanoTime()
    val result = actual()
    val elapsedMs = (System.nanoTime() - start) / 1000000
    println("Result is $result with $elapsedMs ms")
    assertEquals(expected, result)
}