import days.Day1
import days.Day2

fun main() {
    listOf(
        Day1(),
        Day2()
    ).forEach { it
        .printHeader()
        .executePart1()
        .executePart2()
    }
}