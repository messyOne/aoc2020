package days

import java.io.File

class Day15 : Day {
    private val data = File("src/main/resources/data15.txt").useLines { it.toList() }.flatMap { s: String -> s.split(',').map { it.toInt() } }


    override fun executePart1() {
        val last = calculate(2020)

        println("Part 1: $last")
    }

    private fun calculate(until: Int): Int {
        val numbersSpoken = mutableMapOf<Int, Int>().withDefault { -1 }
        val numbersLast = mutableMapOf<Int, Int>().withDefault { 1 }
        val numbersLastBefore = mutableMapOf<Int, Int>().withDefault { 0 }

        data.forEachIndexed { index, i ->
            numbersSpoken[i] = 0
            numbersLast[i] = index + 1
        }

        var last = data.last()
        for (i in data.size + 1..until) {
            if (numbersSpoken[last] == 0) {
                numbersSpoken[0] = numbersSpoken.getValue(0) + 1
                numbersLastBefore[0] = numbersLast.getValue(0)
                numbersLast[0] = i

                last = 0
            } else {
                val new = numbersLast.getValue(last) - numbersLastBefore.getValue(last)

                numbersSpoken[new] = numbersSpoken.getValue(new) + 1
                numbersLastBefore[new] = numbersLast.getValue(new)
                numbersLast[new] = i

                last = new
            }
        }
        return last
    }

    override fun executePart2() {
        val last = calculate(30000000)

        println("Part 2: $last")
    }
}