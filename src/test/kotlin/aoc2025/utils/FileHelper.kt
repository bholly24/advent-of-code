package aoc2025.utils

object FileHelper {
    private const val testRoot = "src/test/kotlin/aoc2025/"
    fun testFileForDay(day: Int): String = "${testRoot}day${getDay(day)}/input.txt"
    fun puzzleFileForDay(day: Int): String = "${testRoot}day${getDay(day)}/puzzle.txt"
    fun getAdditionalTestFile(day: Int, file: String) = "${testRoot}day${getDay(day)}/$file.txt"
    private fun getDay(day: Int): String = "${if (day < 10) "0$day" else day}"
}