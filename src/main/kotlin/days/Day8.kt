package days

import java.io.File
import java.lang.IllegalArgumentException

class Day8 : Day {
    data class Operation(var instruction: String, val value: Int)

    private val operations: MutableList<Operation> = mutableListOf()
    private val handledInstructions: MutableList<Int> = mutableListOf()
    private var accumulator: Int = 0
    private var pointer = 0

    private var lastModificationPointer = -1

    init {
        reset()
        parseData()
    }

    private fun parseData() {
        File("src/main/resources/data8.txt").forEachLine { line ->
            val op = line.split(" ")
            operations.add(Operation(op[0], op[1].toInt()))
        }
    }

    override fun executePart1(): Day {
        while (pointer < operations.size) {
            if (handledInstructions.contains(pointer)) {
                println("Part 1: $accumulator")
                return this
            }

            executeOperations()
        }

        return this
    }

    override fun executePart2(): Day {
        reset()

        while (pointer < operations.size) {
            if (handledInstructions.contains(pointer)) {
                tryFixingIt()
            }

            executeOperations()
        }

        println("Part 2: $accumulator")

        return this
    }

    private fun tryFixingIt() {
        if (lastModificationPointer != -1) {
            if (operations[lastModificationPointer].instruction == "nop") {
                operations[lastModificationPointer].instruction = "jmp"
            } else {
                operations[lastModificationPointer].instruction = "nop"
            }
        }

        for (i in lastModificationPointer + 1 until operations.size) {
            if (listOf("nop", "jmp").contains(operations[i].instruction)) {
                lastModificationPointer = i

                if (operations[i].instruction == "nop") {
                    operations[i].instruction = "jmp"
                } else {
                    operations[i].instruction = "nop"
                }
                break
            }
        }

        reset()
    }

    private fun reset() {
        pointer = 0
        accumulator = 0
        handledInstructions.clear()
    }

    private fun executeOperations() {
        handledInstructions.add(pointer)

        when (operations[pointer].instruction) {
            "nop" -> pointer++
            "acc" -> {
                accumulator += operations[pointer].value
                pointer++
            }
            "jmp" -> {
                pointer += operations[pointer].value
            }
            else -> throw IllegalArgumentException(operations[pointer].instruction)
        }
    }

}