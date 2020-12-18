package days

import java.io.File

class `Day17-2` : Day {
    private val cubes = mutableListOf<Cube>()
    private lateinit var xDim: Dimension
    private lateinit var yDim: Dimension
    private lateinit var zDim: Dimension
    private lateinit var wDim: Dimension

    data class Dimension(var min: Int = 0, var max: Int = 0) {
        fun expand() {
            min -= 1
            max += 1
        }
    }
    data class Cube(val x: Int = 0, val y: Int = 0, val z: Int = 0, val w: Int = 0) {
        fun copy(): Cube {
            return Cube(x, y, z, w)
        }
    }

    init {
        parseData()
    }

    private fun parseData() {
        var y = 0
        File("src/main/resources/data17.txt").forEachLine { line ->
                xDim = Dimension(0, line.length-1)

                line.forEachIndexed { i, c ->
                    if (c == '#') {
                        cubes.add(Cube(i, y))
                    }
            }
            y++
        }
        yDim = Dimension(0, y-1)
        zDim = Dimension(0, 0)
        wDim = Dimension(0, 0)
    }

    private fun traverse(cubes: List<Cube>): MutableList<Cube> {
        listOf(xDim, yDim, zDim, wDim).forEach { it.expand() }

        val newCubes = mutableListOf<Cube>()

        for (w in wDim.min..wDim.max) {
            for (z in zDim.min..zDim.max) {
                for (y in yDim.min..yDim.max) {
                    for (x in xDim.min..xDim.max) {
                        val maybeCube = cubes.find { cube -> cube.x == x && cube.y == y && cube.z == z && cube.w == w }

                        val activeNeighbors = countActiveNeighbors(
                            x,
                            y,
                            z,
                            w,
                            cubes.filterNot { cube -> cube.x == x && cube.y == y && cube.z == z && cube.w == w})

                        if (maybeCube != null) {
                            if (activeNeighbors == 2 || activeNeighbors == 3) {
                                newCubes.add(maybeCube.copy())
                            }
                        } else {
                            if (activeNeighbors == 3) {
                                newCubes.add(Cube(x, y, z, w))
                            }
                        }
                    }
                }
            }
        }

        return newCubes
    }

    override fun executePart1() {
    }

    private fun countActiveNeighbors(xC: Int, yC: Int, zC: Int, wC: Int, cubes: List<Cube>): Int {
        var total = 0

        for (w in (wC + -1)..(wC + 1)) {
            for (z in (zC + -1)..(zC + 1)) {
                for (y in (yC + -1)..(yC + 1)) {
                    for (x in (xC + -1)..(xC + 1)) {
                        val c = cubes.find { it.x == x && it.y == y && it.z == z && it.w == w}

                        if (c != null) {
                            total += 1
                        }
                    }
                }
            }
        }

        return total
    }

    override fun executePart2() {
        val newCubes = (1..6).fold(cubes, { acc, _ ->
            traverse(acc)
        })

//        val minZ = newCubes.minOf { cube: Cube -> cube.z }
//        val maxZ = newCubes.maxOf { cube: Cube -> cube.z }
//        val minY = newCubes.minOf { cube: Cube -> cube.y }
//        val maxY = newCubes.maxOf { cube: Cube -> cube.y }
//        val minX = newCubes.minOf { cube: Cube -> cube.x }
//        val maxX = newCubes.maxOf { cube: Cube -> cube.x }
//
//        for (z in minZ..maxZ) {
//            print("z=$z")
//            for (y in minY..maxY) {
//                println()
//                for (x in minX..maxX) {
//                    val c = newCubes.find { it.x == x && it.y == y && it.z == z }
//
//                    if (c != null) {
//                        print('#')
//                    } else {
//                        print('.')
//                    }
//                }
//            }
//            println()
//            println()
//        }

        println("Part 2: " + newCubes.size)
    }
}