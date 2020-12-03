package days

import java.io.File

class Day3 : Day {
    private val data = File("src/main/resources/data3.txt").useLines { it.toList().map { line -> line.toList() } }

    override fun executePart1(): Day {
        var counter = 0
        var x = 0
        var y = 0

        while (y < data.size) {
            if (data[y][x] == '#') {
                counter++
            }

            y++
            x = (x + 3) % data[0].size
        }

        println("Part 1: $counter")

        return this
    }

    override fun executePart2(): Day {
        return this
    }
}