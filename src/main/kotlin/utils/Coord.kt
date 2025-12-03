package utils

open class Coord(val x: Int, val y: Int) {
    fun getSurroundingCoords(): List<Coord> {
        return listOf(
            Coord(x + 1, y),
        Coord(x - 1, y),
        Coord(x, y + 1),
        Coord(x, y - 1)
        )
    }

    fun add(other: Coord): Coord = Coord(x + other.x, y + other.y)

    fun equals(other: Coord): Boolean = x == other.x && y == other.y
}
