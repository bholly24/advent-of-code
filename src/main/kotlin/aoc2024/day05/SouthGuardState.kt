package aoc2024.day05

import aoc2024.day05.GuardState

class SouthGuardState : GuardState {
    override fun getStraightCharacter(): Char = 'V'
    override fun getTurnCharacter(): Char = '<'
    override fun getIndexDiffsToCheck() = Pair(1, 0)
}