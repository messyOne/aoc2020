import days.Day1
import days.Day2
import days.Day3

fun main() {
    listOf(
        Day1(),
        Day2(),
        Day3()
    ).forEach { it
        .printHeader()
        .executePart1()
        .executePart2()
    }
}