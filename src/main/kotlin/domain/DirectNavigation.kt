package domain

data class Instruction(val type: Char, val amount: Int)
data class Result(val north: Int, val east: Int)

object DirectNavigation {
    var north = 0
    var east = 0
    var direction = 'E'

    fun calculate(data : List<Instruction>): Result {
        data.forEach { instruction ->
            handleDirections(instruction.type, instruction.amount)

            when (instruction.type) {
                'F' -> handleDirections(direction, instruction.amount)
                'R' -> {
                    val steps = instruction.amount/90

                    for (i in 1..steps) {
                        turnRight()
                    }
                }
                'L' -> {
                    val steps = instruction.amount/90

                    for (i in 1..steps) {
                        turnLeft()
                    }
                }
            }
        }

        return Result(north, east)
    }

    private fun turnRight() {
        when (direction) {
            'N' -> direction = 'E'
            'E' -> direction = 'S'
            'S' -> direction = 'W'
            'W' -> direction = 'N'
        }
    }

    private fun turnLeft() {
        when (direction) {
            'N' -> direction = 'W'
            'W' -> direction = 'S'
            'S' -> direction = 'E'
            'E' -> direction = 'N'
        }
    }

    private fun handleDirections(type: Char, amount: Int) {
        when (type) {
            'N' -> north += amount
            'S' -> north -= amount
            'W' -> east -= amount
            'E' -> east += amount
        }
    }
}