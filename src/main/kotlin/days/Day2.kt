package days

import java.io.File

class Day2 : Day {
    data class Policy(val min: Int, val max: Int, val char: String, val input: String)

    private val regex = Regex("(\\d*)-(\\d*)\\s(.):\\s(.*)")
    private val policies: List<Policy> = File("src/main/resources/data2.txt").useLines {
        it.toList().map { line ->
            val match = regex.find(line)!!
            val (min, max, char, input) = match.destructured

            Policy(min.toInt(), max.toInt(), char, input)
        }
    }

    override fun executePart1() {
        val valid = policies.filter { policy ->
            val count = policy.input.count { policy.char.contains(it) }
            count >= policy.min && count <= policy.max
        }.count()

        println("Part 1: $valid")
    }

    override fun executePart2() {
        val valid = policies.filter { policy ->
            var counter = 0

            if (policy.input[policy.min-1] == policy.char[0]) {
                counter++
            }

            if (policy.input[policy.max-1] == policy.char[0]) {
                counter++
            }
            counter == 1
        }.count()

        println("Part 2: $valid")
    }
}