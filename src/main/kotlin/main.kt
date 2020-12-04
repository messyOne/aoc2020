import days.Day1
import days.Day2
import days.Day3
import days.Day4

fun main() {
    listOf(
        Day1(),
        Day2(),
        Day3(),
        Day4()
    ).forEach { it
        .printHeader()
        .executePart1()
        .executePart2()
    }
}