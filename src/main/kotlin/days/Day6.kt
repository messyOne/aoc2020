package days

import java.io.File

class Day6 : Day {
    data class Answer(val answerString: String, val persons: Int)

    private val answers: MutableList<Answer> = mutableListOf()

    init {
        parseData()
    }

    private fun parseData() {
        var answerString = ""
        var persons = 0

        File("src/main/resources/data6.txt").forEachLine { line ->
            if (line.isEmpty()) {
                createAnswerObject(answerString, persons)

                answerString = ""
                persons = 0
            } else {
                persons++
                answerString += line
            }
        }
        // handle the last element
        createAnswerObject(answerString, persons)
    }

    private fun createAnswerObject(answerString: String, persons: Int) {
        answers.add(Answer(answerString, persons))
    }

    override fun executePart1() {
        println("Part 1: " + answers.map { answer -> answer.answerString.toSet() }.sumBy { set -> set.size })
    }

    override fun executePart2() {
        val sum = answers.map { answer ->
            answer.answerString.filter { c -> answer.answerString.count { x -> x == c } == answer.persons}.length / answer.persons
        }.sum()

        println("Part 2: $sum")
    }
}