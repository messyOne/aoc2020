package days

import java.io.File
import kotlin.math.pow

class Day14 : Day {
    private val data = File("src/main/resources/data14.txt").useLines { it.toList() }

    override fun executePart1(): Day {
        var mask = ""
        val results = mutableMapOf<Int, Long>()

        data.forEach { line ->
            if (line.contains("mask")) {
                mask = line.split("=").last().trim()
            } else {
                val (_, address, value) = "\\[(\\d*)\\] = (\\d*)".toRegex().find(line)?.groupValues!!

                var binary = Integer.toBinaryString(value.toInt()).padStart(mask.length, '0')

                mask.forEachIndexed { index, c ->
                    if (c != 'X') {
                        binary = binary.replaceRange(index, index+1, c.toString())
                    }
                }

                var new = 0.0
                binary.reversed().forEachIndexed { index, c ->
                    if (c == '1') {
                        new += 2.0.pow(index)
                    }
                }

                results[address.toInt()] = new.toLong()
            }
        }

        println("Part 1: " + results.values.sum())

        return this
    }

    override fun executePart2(): Day {
        TODO("Not yet implemented")
    }
}