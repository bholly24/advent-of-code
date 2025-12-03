import aoc2024.day00.DayZero
import aoc2024.day01.DayOne
import aoc2024.day02.Day2
import aoc2024.day03.DayThree
import aoc2024.day04.DayFour
import aoc2024.day09.DayNine
import aoc2024.day11.DayEleven
import aoc2024.day12.DayTwelve
import aoc2024.day13.DayThirteen
import aoc2024.day14.DayFourteen
import aoc2024.fileHelper.FileHelper
import aoc2024.printer.AdventPrinter

fun main() {
    AdventPrinter.introduction()

    AdventPrinter.partOne(0)
    val dayZero = DayZero(FileHelper.puzzleFileForDay(0))
    dayZero.partA()

    AdventPrinter.partTwo(0)
    dayZero.partB()

    AdventPrinter.partOne(1)
    val dayOne = DayOne(FileHelper.puzzleFileForDay(1))
    dayOne.partA()

    AdventPrinter.partTwo(1)
    dayOne.partB()

    AdventPrinter.partOne(2)
    val dayTwo = Day2(FileHelper.puzzleFileForDay(2))
    dayTwo.partA()

    AdventPrinter.partTwo(2)
    dayTwo.partB()


    AdventPrinter.partOne(3)
    val dayThree = DayThree(FileHelper.puzzleFileForDay(3))
    dayThree.partA()

    AdventPrinter.partTwo(3)
    dayThree.partB()

    AdventPrinter.partOne(4)
    val dayFour = DayFour(FileHelper.puzzleFileForDay(4))
    dayFour.partA()
    AdventPrinter.partTwo(4)
    dayFour.partB()

//    AdventPrinter.partOne(5)
//    val dayFive = DayFive(FileHelper.puzzleFileForDay(5))
//    dayFive.partA()
//
//    AdventPrinter.partTwo(5)
//    dayFive.partB()

//    AdventPrinter.partOne(6)
//    val daySix = DaySix(FileHelper.puzzleFileForDay(6))
//    daySix.partA()
//
//    AdventPrinter.partTwo(6)
//    AdventPrinter.tooSlowToRunInAdventLoop(6, 'b')

//    daySix.partB()
//    AdventPrinter.partOne(7)
//    var daySeven = DaySeven(FileHelper.puzzleFileForDay(7))
//    daySeven.partA()
//
//    AdventPrinter.partTwo(7)
//    daySeven = DaySeven(FileHelper.puzzleFileForDay(7))
//    daySeven.partB()
//
//    AdventPrinter.partOne(8)
//    val dayEight = DayEight(FileHelper.puzzleFileForDay(8))
//    dayEight.partA()

//    AdventPrinter.partTwo(8)
//    dayEight.partB()

    AdventPrinter.partOne(9)
    val dayNine = DayNine(FileHelper.puzzleFileForDay(9))
    dayNine.partA()

    dayNine.partB()

//    AdventPrinter.partOne(10)
//    val dayTen = DayTen(FileHelper.puzzleFileForDay(10))
//    dayTen.partA()
//
//    AdventPrinter.partTwo(10)
//    dayTen.partB()

    AdventPrinter.partOne(11)
    val dayEleven = DayEleven(FileHelper.puzzleFileForDay(11))
    dayEleven.partA()

    val dayTwelve = DayTwelve(FileHelper.puzzleFileForDay(12))
    dayTwelve.partA()
    dayTwelve.partASolve()

    dayTwelve.partB()

    AdventPrinter.partOne(13)
    var dayThirteen = DayThirteen(FileHelper.puzzleFileForDay(13), 103, 101)
    dayThirteen.partA()
    dayThirteen = DayThirteen(FileHelper.puzzleFileForDay(13), 103, 101)
    dayThirteen.partB()

    val dayFourteen = DayFourteen(FileHelper.puzzleFileForDay(14))
//    dayFourteen.partA()
}
