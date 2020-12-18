package days

interface Day {
    fun printHeader() {
        println("\nSolutions for " + javaClass.simpleName + "\n---------------------")
    }

    fun executePart1()
    fun executePart2()
}