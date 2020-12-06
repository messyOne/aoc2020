import days.*

fun main() {
    listOf(
        Day1(),
        Day2(),
        Day3(),
        Day4(),
        Day5(),
        Day6()
    ).forEach { it
        .printHeader()
        .executePart1()
        .executePart2()
    }
}