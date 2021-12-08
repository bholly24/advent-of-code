import binarydecoder.BinaryDecoder
import crabs.ExponentialCrabCommander
import crabs.LinearCrabCommander
import depthreader.DepthReader
import lanternfish.LanternMap
import lanternfish.Lanternfish
import lanternfish.readInLines

fun main(args: Array<String>) {
    println("Day 1")
    val reader = DepthReader()
    val depthReadings = reader.readInFile("src/main/kotlin/depthreader/input.txt")
    val singleDepthChanges = reader.countDepthIncreases(depthReadings)
    println("Single count depth changes: $singleDepthChanges")

    println("Day 1 Part 2")
    val rollingDepthChanges = reader.countRollingDepthIncreases(depthReadings)
    println("Rolling count depth changes: $rollingDepthChanges")

    println("Day 3")
    val decoder = BinaryDecoder()
    val binaryReadings = decoder.readInBinaryNumbers("src/main/kotlin/binarydecoder/binary-input.txt")
    val powerConsumption = decoder.getPowerConsumption(binaryReadings)

    println("Day 3 Part 2")
    val lifeSupportRating = decoder.getLifeSupportRating(binaryReadings)
    println("Power consumption: $powerConsumption")
    println("Life support rating: $lifeSupportRating")

    println("Day 6")
    val lanternInput = readInLines("src/main/kotlin/lanternfish/input.txt")
    val lanternfish = Lanternfish(lanternInput)
    lanternfish.runSimulation(80)

    println("Day 6 Part 2")
    val lanternMap = LanternMap(lanternInput)
    lanternMap.runSimulationAndGetFishTotal(256)

    println("Day 7")
    val crabInput = readInLines("src/main/kotlin/crabs/input.txt").toList()
    val linearCommander = LinearCrabCommander(crabInput)
    linearCommander.alignCrabs()

    println("Day 7 Part 2")
    val exponentialCrabCommander = ExponentialCrabCommander(crabInput)
    exponentialCrabCommander.alignCrabs()
}