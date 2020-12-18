package days

import java.io.File
import kotlin.math.pow

class Day14 : Day {
    private val data = File("src/main/resources/data14.txt").useLines { it.toList() }

    override fun executePart1() {
        var mask = ""
        val results = mutableMapOf<Int, Long>()

        data.forEach { line ->
            if (line.contains("mask")) {
                mask = line.split("=").last().trim()
            } else {
                val (_, address, value) = "\\[(\\d*)\\] = (\\d*)".toRegex().find(line)?.groupValues!!

                var binary = Integer.toBinaryString(value.toInt()).padStart(mask.length, '0')

                mask.forEachIndexed { index, c ->
                    if (c != 'X') {
                        binary = binary.replaceRange(index, index+1, c.toString())
                    }
                }

                var new = 0.0
                binary.reversed().forEachIndexed { index, c ->
                    if (c == '1') {
                        new += 2.0.pow(index)
                    }
                }

                results[address.toInt()] = new.toLong()
            }
        }

        println("Part 1: " + results.values.sum())
    }

    override fun executePart2() {
        var mask = ""
        val results = mutableMapOf<Long, Long>()

        data.forEach { line ->
            if (line.contains("mask")) {
                mask = line.split("=").last().trim()
            } else {
                val (_, address, value) = "\\[(\\d*)\\] = (\\d*)".toRegex().find(line)?.groupValues!!

                fun replace(b: String, index: Int) {
                    if (index == mask.length) {
                        var a = 0.0
                        b.reversed().forEachIndexed { i, c ->
                            if (c == '1') {
                                a += 2.0.pow(i)
                            }
                        }

                        results[a.toLong()] = value.toLong()

                        return
                    }

                    when (val c = mask[index]) {
                        '1' -> replace(b.replaceRange(index, index+1, c.toString()), index + 1)
                        '0' -> replace(b, index + 1)
                        'X' -> {
                            replace(b.replaceRange(index, index+1, "1"), index + 1)
                            replace(b.replaceRange(index, index+1, "0"), index + 1)
                        }
                    }
                }

                val binary = Integer.toBinaryString(address.toInt()).padStart(mask.length, '0')
                replace(binary, 0)
            }
        }
        println("Part 2: " + results.values.sum())
    }
}