package aoc2025.day10

import java.io.File

class Solver(filePath: String) {
    private val machines = File(filePath)
        .readLines()
        .map {
            val s = it.split("[").drop(1).first().split("]").first().map { ch -> ch != '.' }
            val buttons = it.split(")")
                .dropLast(1)
                .map { i ->
                    i.split("(").drop(1).first()
                        .split(",")
                        .filter { u -> u.isNotEmpty() }
                        .map(String::toInt)
                }
            Machine(s, buttons, listOf())
        }

    fun partA(): Int {
        return machines.sumOf { bfsMinimumPresses(it) }
    }

    fun partB(): Int {
        return 0
    }

    private fun bfsMinimumPresses(machine: Machine): Int {
        val queue = ArrayDeque(machine.buttons.map { QueueObj(machine.lights.map { it }) })
        // Store any patterns that result in 0 after n iterations as a string that is bad
        val badPatterns = mutableListOf<String>()
        while (queue.isNotEmpty()) {
            val d = queue.removeFirst()
            if (badPatterns.contains(d.buttons.toString())) {
                continue
            }
            for (button in machine.buttons) {
                val m = Machine(machine.lightSchematic, machine.buttons, presses = d.presses, lights = d.lights.map { it }.toMutableList() )
                m.press(button)
                if (m.lights == m.lightSchematic) {
                    return m.presses
                } else if (m.lights == List(m.lights.size) { false }) {
                    badPatterns.add(d.buttons.toString())
                } else {
                    val buttons = d.buttons + listOf(button)
                    queue.add(QueueObj(m.lights.map { it }, m.presses, buttons))
                }
            }
        }
        return 0
    }
}

data class QueueObj(val lights: List<Boolean>, val presses: Int = 0, val buttons: List<List<Int>> = listOf())

// For each button, copy the machine. Press the button. Increment counter by one. Then, repeat this process for each button.

data class Machine(
    val lightSchematic: List<Boolean>,
    val buttons: List<List<Int>>,
    val joltageRequirements: List<Int> = listOf(),
    var presses: Int = 0,
    val lights: MutableList<Boolean> = MutableList(lightSchematic.size) { false }
) {
    fun press(buttons: List<Int>) {
        presses += 1
        for (b in buttons) {
            lights[b] = !lights[b]
        }
    }
}
