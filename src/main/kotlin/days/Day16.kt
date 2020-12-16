package days

import java.io.File

class Day16 : Day {
    data class Rule(val type: String, val minA: Int, val maxA: Int, val minB: Int, val maxB: Int) {
        fun isValid(i: Int): Boolean {
            return i in minA..maxA || i in minB..maxB
        }
    }

    enum class State {
        DATA, OWN, NEARBY
    }

    private val rules = mutableListOf<Rule>()
    private var own = listOf<Int>()
    private val nearby = mutableListOf<List<Int>>()

    init {
        parseData()
    }

    private fun parseData() {
        var state = State.DATA

        val dataR = Regex("(.*): (\\d*)-(\\d*) or (\\d*)-(\\d*)")

        File("src/main/resources/data16.txt").forEachLine { line ->
            if (line.isNotEmpty()) {
                if (line.contains("your ticket:")) {
                    state = State.OWN
                }

                if (line.contains("nearby tickets:")) {
                    state = State.NEARBY
                }

                when (state) {
                    State.DATA -> {
                        val matchData = dataR.find(line)!!

                        val (type, minA, maxA, minB, maxB) = matchData.destructured

                        rules.add(Rule(type, minA.toInt(), maxA.toInt(), minB.toInt(), maxB.toInt()))
                    }
                    State.OWN -> {
                        if (!line.contains("your ticket:")) {
                            own = line.split(',').map { it.toInt() }
                        }
                    }
                    State.NEARBY -> {
                        if (!line.contains("nearby tickets:")) {
                            nearby.add(line.split(',').map { it.toInt() })
                        }
                    }
                }
            }
        }
    }

    override fun executePart1(): Day {
        val invalid = mutableListOf<Int>()

        nearby.forEach { list ->
            list.forEach { i ->
                if (!rules.any { rule -> rule.isValid(i) }) {
                    invalid.add(i)
                }
            }
        }

        println("Part 1: " + invalid.sum())

        return this
    }

    override fun executePart2(): Day {
        TODO("Not yet implemented")
    }
}