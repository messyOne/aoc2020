package days

import java.io.File

class Day17 : Day {
    private val cubes = mutableListOf<Cube>()

    data class Cube(val x: Int = 0, val y: Int = 0, val z: Int = 0, var isActive: Boolean = false) {
        fun copy(isActive: Boolean): Cube {
            return Cube(x, y, z, isActive)
        }
    }

    init {
        parseData()
    }

    private fun parseData() {
        var y = 0
        File("src/main/resources/data17.txt").forEachLine { line ->
            line.forEachIndexed { i, c ->
                cubes.add(Cube(i, y, isActive = (c == '#')))
            }
            y++
        }
    }

    private fun traverse(cubes: List<Cube>): MutableList<Cube> {
        val expandedCubes = cubes.fold(cubes, { s, c ->
            expand(c, s).toMutableList()
        })

        val newCubes = mutableListOf<Cube>()

        expandedCubes.forEach { cube ->
            val activeNeighbors = countActiveNeighbors(cube, expandedCubes)

            val find = expandedCubes.find { it == cube }!!
            if (cube.isActive) {
                newCubes.add(find.copy(activeNeighbors == 2 || activeNeighbors == 3))
            } else {
                newCubes.add(find.copy(activeNeighbors == 3))
            }
        }

        return newCubes
    }

    override fun executePart1(): Day {
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
//                        print(if (c.isActive) '#' else '.' )
//                    } else {
//                        print('X')
//                    }
//                }
//            }
//            println()
//            println()
//        }

        println("Part 1: " + newCubes.count { it.isActive })

        return this
    }

    private fun expand(cube: Cube, cubes: List<Cube>): List<Cube> {
        val new = cubes.toMutableList()

        for (z in (cube.z + -1)..(cube.z + 1)) {
            for (y in (cube.y + -1)..(cube.y + 1)) {
                for (x in (cube.x + -1)..(cube.x + 1)) {
                    val c = cubes.find { it.x == x && it.y == y && it.z == z }

                    if (c == null) {
                        new.add(Cube(x, y, z))
                    }
                }
            }
        }

        return new
    }

    private fun countActiveNeighbors(cube: Cube, cubes: List<Cube>): Int {
        var total = 0

        for (z in (cube.z + -1)..(cube.z + 1)) {
            for (y in (cube.y + -1)..(cube.y + 1)) {
                for (x in (cube.x + -1)..(cube.x + 1)) {
                    val c = cubes.find { it.x == x && it.y == y && it.z == z }

                    if (c != null && c != cube && c.isActive) {
                        total += 1
                    }
                }
            }
        }

        return total
    }

    override fun executePart2(): Day {
        TODO("Not yet implemented")
    }
}