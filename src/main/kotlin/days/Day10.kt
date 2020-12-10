package days

import java.io.File

class Day10 : Day {
    private val data = File("src/main/resources/data10.txt").useLines { it.toList().map { s -> s.toInt() } }

    override fun executePart1(): Day {
        var start = 0
        var jolt1 = 0
        var jolt3 = 1 // last one

        for (jolt in data.sorted()) {
            when (jolt-start) {
                1 -> jolt1++
                3 -> jolt3++
            }

            start = jolt
        }

        println("Part 1: " + jolt1 * jolt3)

        return this
    }

    override fun executePart2(): Day {
        println("Part 2: Missing")

        return this
    }

}