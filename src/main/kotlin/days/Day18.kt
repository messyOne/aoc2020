package days

import java.io.File

class Day18 : Day {
    private val data: List<String> = File("src/main/resources/data18.txt").useLines { it.toList() }

    override fun executePart1() {
        val regex = Regex("\\([\\d\\+\\*\\s]*\\)")
        val total = data.map {
            var s = it

            while (s.contains('(')) {
                s = regex.replace(s) { m ->
                    calculatePart1(
                        m.value
                            .replace("(", "")
                            .replace(")", "")
                    ).toString()
                }
            }

            calculatePart1(s)
        }.sum()

        println("Part 1: $total")
    }

    private fun calculatePart1(formula: String): Long {
        val parts = formula.split(' ')
        var op = "+"
        var total = 0L

        parts.forEach {
            when (it) {
                "+" -> op = it
                "*" -> op = it
                else -> if (op == "+") {
                    total += it.toLong()
                } else {
                    total *= it.toLong()
                }
            }
        }

        return total
    }

    private fun calculatePart2(formula: String): Long {
        val parts = formula.split(' ').toMutableList()
        var op = "+"
        var total = 0L

        while (parts.contains("+")) {
            val i = parts.indexOf("+")
            val o = parts[i]
            val a = parts[i - 1]
            val b = parts[i + 1]

            val c = calculatePart1("$a $o $b")

            parts.removeAt(i + 1)
            parts.removeAt(i)
            parts.removeAt(i - 1)
            parts.add(i - 1, c.toString())
        }

        parts.forEach {
            when (it) {
                "+" -> throw Exception("+ should already be handled")
                "*" -> op = it
                else -> if (op == "+") {
                    total += it.toLong()
                } else {
                    total *= it.toLong()
                }
            }
        }

        return total
    }

    override fun executePart2() {
        val regex = Regex("\\([\\d\\+\\*\\s]*\\)")
        val total = data.map {
            var s = it

            while (s.contains('(')) {
                s = regex.replace(s) { m ->
                    calculatePart2(
                        m.value.replace("(", "")
                            .replace(")", "")
                    ).toString()
                }
            }

            calculatePart2(s)
        }.sum()

        println("Part 2: $total")
    }
}