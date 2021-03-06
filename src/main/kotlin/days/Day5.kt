package days

import java.io.File

class Day5 : Day {
    private val data: List<List<Char>> =
        File("src/main/resources/data5.txt").useLines { it.toList().map { line -> line.toList() } }
    val MIN_ROW = 0
    val MAX_ROW = 127

    val MIN_COL = 0
    val MAX_COL = 7

    override fun executePart1() {
        var topSeat = 0

        data.map { line ->
            val seatId = getSeatId(line)

            if (seatId > topSeat) {
                topSeat = seatId
            }
        }

        println("Part 1: $topSeat")
    }

    override fun executePart2() {
        val allSeats = (0..127*8+7).toMutableList()

        data.map { line ->
            val seatId = getSeatId(line)

            allSeats.remove(seatId)
        }

        for (i in 0..allSeats.size-2) {
            if (allSeats[i+1] - allSeats[i] > 1) {
                println("Part 2: " + allSeats[i+1])
                break
            }
        }
    }

    private fun getSeatId(line: List<Char>): Int {
        val row = traverse(line, 0, MIN_ROW, MAX_ROW)
        val column = traverse(line.takeLast(3), 0, MIN_COL, MAX_COL)
        val seatId = row * 8 + column
        return seatId
    }

    private fun traverse(line: List<Char>, pointer: Int, min: Int, max: Int): Int {
//        println("Min: $min, Max: $max")

        if (min == max) {
            return min
        }

        val center = min + (max - min) / 2

        return when (line[pointer]) {
            'F', 'L' -> traverse(line, pointer + 1, min, center)
            'B', 'R' -> traverse(line, pointer + 1, center+1, max) // +1 correct or should be taken into consideration in center calc
            else -> throw IllegalArgumentException(line[pointer].toString())
        }
    }
}