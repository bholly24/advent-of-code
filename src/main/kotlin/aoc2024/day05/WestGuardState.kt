package aoc2024.day05

import aoc2024.day05.GuardState

class WestGuardState : GuardState {
    override fun getStraightCharacter(): Char = '<'
    override fun getTurnCharacter(): Char = '^'
    override fun getIndexDiffsToCheck() = Pair(0, -1)
}