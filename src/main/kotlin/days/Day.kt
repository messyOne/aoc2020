package days

interface Day {
    fun printHeader(): Day {
        println("\nSolutions for " + javaClass.simpleName + "\n---------------------")
        return this
    }

    fun executePart1(): Day;
    fun executePart2(): Day;
}