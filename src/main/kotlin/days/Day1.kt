package days

import java.io.File

class Day1 : Day {
    private val data = File("src/main/resources/data1.txt").useLines { it.toList().map { s -> s.toInt() } }

    override fun executePart1() : Day {
        for (x in data) {
            for (y in data) {
                if (x + y == 2020) {
                    println("Part 1: " + x * y)
                    return this
                }
            }
        }

        return this
    }

    override fun executePart2() : Day {
        for (x in data) {
            for (y in data) {
                for (z in data) {
                    if (x + y + z == 2020) {
                        println("Part 2: " + x * y * z)
                        return this
                    }
                }
            }
        }

        return this
    }
}