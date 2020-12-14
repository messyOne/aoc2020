package days

import domain.Instruction
import java.io.File

class Day13 : Day {
    private val data = File("src/main/resources/data13.txt").useLines { it.toList() }

    override fun executePart1(): Day {
        val timestamp = data.first().toInt()
        val busses = data.last().split(',').filter { s -> s != "x" }.map { it.toInt() }


        val first = busses.map { i ->
            val x = timestamp / i

            i to (x + 1) * i - timestamp
        }.minBy { pair -> pair.second }!!

        println("Part 1: " + first.first * first.second)

        return this
    }

    override fun executePart2(): Day {
        val busses = data.last().split(',').withIndex().filter { pair -> pair.value != "x" }.map { indexedValue -> indexedValue.value.toLong() to indexedValue.index.toLong() }

        var t = 0L
        var step = busses.first().first

        busses.drop(1).forEach { pair ->
            while ((t + pair.second) % pair.first != 0L) {
                t += step
            }
            step *= pair.first
        }

        println("Part 2: $t")

        return this
    }
}