package aoc2021

import aoc2021.day08.SegmentDecoder
import aoc2021.day03.BinaryDecoder
import aoc2021.day07.ExponentialCrabCommander
import aoc2021.day07.LinearCrabCommander
import aoc2021.day10.SyntaxAutoComplete
import aoc2021.day10.SyntaxParser
import aoc2021.day11.OctopusTracker
import aoc2021.day12.CaveMapperPartTwo
import aoc2021.day12.SimpleCaveMapper
import aoc2021.day13.InstructionCreaser
import aoc2021.day06.LanternMap
import aoc2021.day06.Lanternfish
import aoc2021.day06.readInLines
import aoc2021.day09.LavaWalker
import aoc2021.printer.AdventPrinter

fun main(args: Array<String>) {
    AdventPrinter.introduction()
    AdventPrinter.partOne(1)
    val reader = DepthReader()
    val depthReadings = reader.readInFile("src/main/kotlin/day01/input.txt")
    val singleDepthChanges = reader.countDepthIncreases(depthReadings)
    println("Single count depth changes: $singleDepthChanges")

    AdventPrinter.partTwo(1)
    val rollingDepthChanges = reader.countRollingDepthIncreases(depthReadings)
    println("Rolling count depth changes: $rollingDepthChanges")

    AdventPrinter.skippedForNow(2)

    AdventPrinter.partOne(3)
    val decoder = BinaryDecoder()
    val binaryReadings = decoder.readInBinaryNumbers("src/main/kotlin/day03/binary-input.txt")
    val powerConsumption = decoder.getPowerConsumption(binaryReadings)
    println("Power consumption: $powerConsumption")

    AdventPrinter.partTwo(3)
    val lifeSupportRating = decoder.getLifeSupportRating(binaryReadings)
    println("Life support rating: $lifeSupportRating")

    AdventPrinter.skippedForNow(4)
    AdventPrinter.skippedForNow(5)

    AdventPrinter.partOne(6)
    val lanternInput = readInLines("src/main/kotlin/day06/input.txt")
    val lanternfish = Lanternfish(lanternInput)
    lanternfish.runSimulation(80)

    AdventPrinter.partTwo(6)
    val lanternMap = LanternMap(lanternInput)
    lanternMap.runSimulationAndGetFishTotal(256)

    AdventPrinter.partOne(7)
    val crabInput = readInLines("src/main/kotlin/day07/input.txt").toList()
    val linearCommander = LinearCrabCommander(crabInput)
    linearCommander.alignCrabs()

    AdventPrinter.partTwo(7)
    val exponentialCrabCommander = ExponentialCrabCommander(crabInput)
    exponentialCrabCommander.alignCrabs()

    AdventPrinter.partOne(8)
    val segmentDecoder = SegmentDecoder("src/main/kotlin/day08/input.txt")
    segmentDecoder.countDecipherableSegments()

    AdventPrinter.partTwo(8)
    segmentDecoder.decodeAndAddSegments()

    AdventPrinter.partOne(9)
    val lavaWalker = LavaWalker("src/main/kotlin/day09/input.txt")
    lavaWalker.findTotalRisk()

    AdventPrinter.partTwo(9)
    lavaWalker.findBasinSizes()

    AdventPrinter.partOne(10)
    val syntaxParser = SyntaxParser("src/main/kotlin/day10/input.txt")
    syntaxParser.scoreErrors()

    AdventPrinter.partTwo(10)
    val syntaxAutoComplete = SyntaxAutoComplete("src/main/kotlin/day10/input.txt")
    syntaxAutoComplete.completeNonCorruptedLines()

    AdventPrinter.partOne(11)
    val octopusTracker = OctopusTracker("src/main/kotlin/day11/input.txt")
    octopusTracker.predictFlashes(100)

    AdventPrinter.partTwo(11)
    val octopusSynchronizer = OctopusTracker("src/main/kotlin/day11/input.txt")
    octopusSynchronizer.findSynchronizedFlashStep()

    AdventPrinter.partOne(12)
    val caveMapper = SimpleCaveMapper("src/main/kotlin/day12/input.txt")
    caveMapper.getTotalPaths()

    AdventPrinter.partTwo(12)
    val caveMapperPartTwo = CaveMapperPartTwo("src/main/kotlin/day12/input.txt")
    caveMapperPartTwo.getTotalPaths()

    AdventPrinter.partOne(13)
    val instructionCreaser = InstructionCreaser("src/main/kotlin/day13/input.txt")
    instructionCreaser.foldVertically(655, true)

    AdventPrinter.partTwo(13)
    val instructionCreaserTwo = InstructionCreaser("src/main/kotlin/day13/input.txt")
    instructionCreaserTwo.executeFoldSteps()
}