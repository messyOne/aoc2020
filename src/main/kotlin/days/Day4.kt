package days

import domain.Passport
import java.io.File
import java.lang.IllegalArgumentException

class Day4() : Day {
    var passports: MutableList<Passport> = mutableListOf()

    init {
        parseData()
    }

    private fun parseData() {
        var passportString = ""

        File("src/main/resources/data4.txt").forEachLine { line ->
            if (line.isEmpty()) {
                createPassport(passportString)

                passportString = ""
            } else {
                passportString += " $line"
            }
        }
        // handle the last element
        createPassport(passportString)
    }

    private fun createPassport(passportString: String) {
        passports.add(Passport.create(passportString))
    }

    override fun executePart1() {
        println("Part 1: " + passports.filter { it.isValid() }.size)
    }

    override fun executePart2() {
        println("Part 2: " + passports.filter { it.isValidStrict() }.size)
    }
}