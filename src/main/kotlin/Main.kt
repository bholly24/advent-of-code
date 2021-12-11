import day8.SegmentDecoder
import day3.BinaryDecoder
import day7.ExponentialCrabCommander
import day7.LinearCrabCommander
import day1.DepthReader
import day6.LanternMap
import day6.Lanternfish
import day6.readInLines
import day9.LavaWalker

fun main(args: Array<String>) {
    println("Day 1")
    val reader = DepthReader()
    val depthReadings = reader.readInFile("src/main/kotlin/day1/input.txt")
    val singleDepthChanges = reader.countDepthIncreases(depthReadings)
    println("Single count depth changes: $singleDepthChanges")

    println("Day 1 Part 2")
    val rollingDepthChanges = reader.countRollingDepthIncreases(depthReadings)
    println("Rolling count depth changes: $rollingDepthChanges")

    println("Day 3")
    val decoder = BinaryDecoder()
    val binaryReadings = decoder.readInBinaryNumbers("src/main/kotlin/day3/binary-input.txt")
    val powerConsumption = decoder.getPowerConsumption(binaryReadings)
    println("Power consumption: $powerConsumption")

    println("Day 3 Part 2")
    val lifeSupportRating = decoder.getLifeSupportRating(binaryReadings)
    println("Life support rating: $lifeSupportRating")

    println("Day 6")
    val lanternInput = readInLines("src/main/kotlin/day6/input.txt")
    val lanternfish = Lanternfish(lanternInput)
    lanternfish.runSimulation(80)

    println("Day 6 Part 2")
    val lanternMap = LanternMap(lanternInput)
    lanternMap.runSimulationAndGetFishTotal(256)

    println("Day 7")
    val crabInput = readInLines("src/main/kotlin/day7/input.txt").toList()
    val linearCommander = LinearCrabCommander(crabInput)
    linearCommander.alignCrabs()

    println("Day 7 Part 2")
    val exponentialCrabCommander = ExponentialCrabCommander(crabInput)
    exponentialCrabCommander.alignCrabs()

    println("Day 8")
    val segmentDecoder = SegmentDecoder("src/main/kotlin/day8/input.txt")
    segmentDecoder.countDecipherableSegments()

    println("Day 8 Part 2")
    segmentDecoder.decodeAndAddSegments()

    println("Day 9")
    val lavaWalker = LavaWalker("src/main/kotlin/day9/input.txt")
    lavaWalker.findTotalRisk()

    println("Day 8 Part 2")
    lavaWalker.findBasinSizes()
}