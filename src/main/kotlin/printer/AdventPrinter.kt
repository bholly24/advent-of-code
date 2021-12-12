package printer

object AdventPrinter {
    fun introduction() {
        println("Advent of code entries ")
    }

    fun skippedForNow(day: Int) {
        divider()
        println("Day $day has been skipped for now")
    }

    fun partOne(day: Int) {
        divider()
        println("Day $day")
    }

    fun partTwo(day: Int) {
        println()
        println("Day $day Part 2")
    }

    private fun divider() {
        println("------------------------------")
    }
}