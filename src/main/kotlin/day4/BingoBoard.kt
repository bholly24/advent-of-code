//package bingo
//
//class BingoGame(private val bingoBoards: Array<BingoBoard>) {
//    fun recordNumber(number: Int) {
//        bingoBoards.forEach { it.recordNumber(number) }
//    }
//}
//
//class BingoBoard(boardNumbers: Array<Int>, private val dimension: Int) {
//    var winningNumber: Int = 0
//    var gotABingo: Boolean = true
//    private val bingoNumbers: List<BingoNumber> = boardNumbers.map {BingoNumber(it)}
//
//    fun recordNumber(number: Int) {
//        bingoNumbers.forEach { if (it.number == number) it.wasCalled = true }
//        if (boardHasBingo()) {
//            gotABingo = true
//            val winningScore = calculateScore(number)
//            println("I got a bingo! Final Score is $winningScore")
//        }
//    }
//
//    fun calculateScore(winningNumber: Int): Int {
//        var unmarkedSum = 0
//        bingoNumbers.forEach { if (!it.wasCalled) unmarkedSum += it.number }
//        return unmarkedSum * winningNumber
//    }
//
//    private fun boardHasBingo(): Boolean {
//        rowIndices().forEach {if (hasABingo(it)) return true }
//
//        colIndices().forEach {if (hasABingo(it)) return true }
//
//        return false
//    }
//
//    private fun hasABingo(indices: IntRange): Boolean {
//        return bingoNumbers.slice(indices).map { it.wasCalled }.all { it }
//    }
//
//    private fun colIndices(): List<IntProgression> {
//        val startingNumbers = 0 until dimension
//        val colIndices = mutableListOf<IntProgression>()
//        startingNumbers.forEach {
//            val stoppingPoint = it + (dimension * (dimension - 1))
//            val f = it until stoppingPoint step dimension
//            colIndices.add(f)
//        }
//        return colIndices
//    }
//
//
//    private fun rowIndices(): List<IntRange> {
//        val totalNumbers = bingoNumbers.size
//        val rowIndices = mutableListOf<IntRange>()
//        val startingNumbers = 0 until (totalNumbers - 1) step dimension
//        startingNumbers.forEach {
//            rowIndices.add(it until it + dimension)
//        }
//        return rowIndices
//    }
//}
//
//class BingoNumber(val number: Int) {
//    var wasCalled = false
//}