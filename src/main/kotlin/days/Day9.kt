package days

import java.io.File

class Day9 : Day {
    private val data = File("src/main/resources/data9.txt").useLines { it.toList().map { s -> s.toLong() } }
    private val preamble = 25

    override fun executePart1(): Day {
        println("Part 1: " + findWeakness())

        return this
    }

    override fun executePart2(): Day {
        val weakness = findWeakness()

        for (x in 0 until data.size-1) {
            var number = data[x]
            var smallest = Long.MAX_VALUE
            var highest = 0L

            if (data[x] > highest) {
                highest = data[x]
            }

            if (data[x] < smallest) {
                smallest = data[x]
            }

            for (y in x+1 until data.size) {
                number += data[y]

                if (data[y] > highest) {
                    highest = data[y]
                }

                if (data[y] < smallest) {
                    smallest = data[y]
                }

                if (number == weakness) {
                    println("Part 2: " + (smallest + highest))
                    break
                }
            }
        }

        return this
    }

    private fun findWeakness(): Long {
        for (i in preamble until data.size) {
            val current = data[i]
            var isValid = false

            for (x in i - preamble until i) {
                for (y in i - preamble until i) {
                    if (x != y) {
                        if (data[x] + data[y] == current) {
                            isValid = true
                            break
                        }

                    }
                }
            }

            if (!isValid) {
                return current
            }
        }

        throw Exception("No weakness!")
    }
}