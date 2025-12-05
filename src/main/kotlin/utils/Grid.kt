package utils

open class Grid<T>(val items: List<List<T>>) {
    val yMax = items.size
    val xMax = items[0].size

    fun get(coord: Coord): T {
        return items[coord.y][coord.x]
    }

    fun getNeighbors(point: Coord): List<Coord> {
        return listOf(
        Coord(point.x + 1, point.y),
        Coord(point.x - 1, point.y),
        Coord(point.x, point.y + 1),
        Coord(point.x, point.y - 1)
        ).filter(::isInBounds)
    }

    fun getNeighborsWithDiagonal(point: Coord): List<Coord> {
        return listOf(
            Coord(point.x + 1, point.y),
            Coord(point.x + 1, point.y + 1),
            Coord(point.x + 1, point.y - 1),
            Coord(point.x - 1, point.y - 1),
            Coord(point.x - 1, point.y),
            Coord(point.x - 1, point.y + 1),
            Coord(point.x, point.y + 1),
            Coord(point.x, point.y - 1)
        ).filter(::isInBounds)
    }

    fun getOutOfBoundsNeighbors(point: Coord): List<Coord> {
        return listOf(
            Coord(point.x + 1, point.y),
            Coord(point.x - 1, point.y),
            Coord(point.x, point.y + 1),
            Coord(point.x, point.y - 1)
        ).filter { !isInBounds(it) }
    }

    fun toMutableGrid(): MutableGrid<T> {
        val copiedItems = items.map { it.toMutableList() }.toMutableList()
        return MutableGrid(copiedItems)
    }

    private fun isInBounds(c: Coord): Boolean = c.x in 0 until xMax && c.y in 0 until yMax
}

class MutableGrid<T>(
    private val mutableItems: MutableList<MutableList<T>>
) : Grid<T>(mutableItems) {

    fun set(coord: Coord, value: T) {
        mutableItems[coord.y][coord.x] = value
    }
}