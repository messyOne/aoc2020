package days

import java.io.File
import kotlin.math.min

class Day10 : Day {
    private val data = File("src/main/resources/data10.txt").useLines { it.toList().map { s -> s.toInt() } }.sorted()

    override fun executePart1() {
        var start = 0
        var jolt1 = 0
        var jolt3 = 1 // last one

        for (jolt in data) {
            when (jolt-start) {
                1 -> jolt1++
                3 -> jolt3++
            }

            start = jolt
        }

        println("Part 1: " + jolt1 * jolt3)
    }

    override fun executePart2() {
        val results = mutableMapOf<Int, Long>()
        val list = listOf(0) + data + listOf(data.last()+3)

        fun traverse(i: Int): Long {
            if (i == list.size-1) {
                return 1
            }

            if (results.containsKey(i)) {
                return results.getValue(i)
            }

            var total = 0L

            for (j in i+1 until min(i+4, list.size)) {
                if (list[j] - list[i] <= 3) {
                    total += traverse(j)
                }
            }

            results[i] = total
            return total
        }

        println("Part 2: " + traverse(0))
    }

}