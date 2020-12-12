package days

import domain.Instruction
import domain.DirectNavigation
import domain.WaypointNavigation
import java.io.File
import kotlin.math.abs

class Day12 : Day {
    private val data: List<Instruction> = File("src/main/resources/data12.txt").useLines { it.toList().map { s -> Instruction(s[0], s.drop(1).toInt()) } }

    override fun executePart1(): Day {
        val result = DirectNavigation.calculate(data)

        println("Part 1: " + (abs(result.north) + abs(result.east)))

        return this
    }

    override fun executePart2(): Day {
        val result = WaypointNavigation.calculate(data)

        println("Part 2: " + (abs(result.north) + abs(result.east)))

        return this
    }

}