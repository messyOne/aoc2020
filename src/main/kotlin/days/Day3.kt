package days

import java.io.File

class Day3 : Day {
    private val data = File("src/main/resources/data3.txt").useLines { it.toList().map { line -> line.toList() } }

    override fun executePart1(): Day {
        val counter = calculate(3, 1)

        println("Part 1: $counter")

        return this
    }

    override fun executePart2(): Day {
        val total =
                calculate(1, 1) *
                calculate(3, 1) *
                calculate(5, 1) *
                calculate(7, 1) *
                calculate(1, 2)

        println("Part 2: $total")

        return this
    }

    private fun calculate(xIncrement: Int, yIncrement: Int): Int {
        var counter = 0
        var x = 0
        var y = 0

        while (y < data.size) {
            if (data[y][x] == '#') {
                counter++
            }

            y += yIncrement
            x = (x + xIncrement) % data[0].size
        }
        return counter
    }
}