package days

import java.io.File

class Day11 : Day {
    data class Position(var type: Char)

    private val EMPTY = 'L'
    private val FLOOR = '.'
    private val OCCUPIED = '#'

    private var data: List<List<Position>> = File("src/main/resources/data11.txt").useLines { it.toList().map { s -> s.map { c: Char -> Position(c) } } }

    override fun executePart1(): Day {

        fun traverse(list: List<List<Position>>): Int {
            val newList = applyRules(list)

            return if (newList != list) {
                traverse(newList)
            } else {
                newList.map { mutableList -> mutableList.count { position -> position.type == OCCUPIED } }.sum()
            }
        }

        println("Part 1: " + traverse(data))

        return this
    }

    private fun applyRules(data: List<List<Position>>): MutableList<MutableList<Position>> {
        val newData: MutableList<MutableList<Position>> = mutableListOf()

        for (row in data.withIndex()) {
            newData.add(row.index, mutableListOf())
            for (cell in row.value.withIndex()) {
                newData[row.index].add(cell.index, Position(data[row.index][cell.index].type))

                when (cell.value.type) {
                    EMPTY -> if (getAdjacentOccupied(data, row.index, cell.index) == 0) {
                        newData[row.index][cell.index].type = OCCUPIED
                    }
                    OCCUPIED -> if (getAdjacentOccupied(data, row.index, cell.index) >= 4) {
                        newData[row.index][cell.index].type = EMPTY
                    }
                }
            }
        }
        return newData
    }

    private fun getAdjacentOccupied(data: List<List<Position>>, y: Int, x: Int): Int {
        var total = 0

        for (i in -1..1) {
            for (j in -1..1) {
                val yC = y + i
                val xC = x + j

                if ((xC < 0 || xC > data[0].size-1) ||
                    (yC < 0 || yC > data.size-1) ||
                    (i == 0 && j == 0)) {
                    continue
                }

                when (data[yC][xC].type) {
                    OCCUPIED ->
                        total++
                }
            }
        }

        return total
    }

    override fun executePart2(): Day {
        TODO("Not yet implemented")
    }
}