package days

import java.io.File
import java.lang.IllegalArgumentException

class Day4() : Day {

    data class Passport(
        var byr: String = "",
        var iyr: String = "",
        var eyr: String = "",
        var hgt: String = "",
        var hcl: String = "",
        var ecl: String = "",
        var pid: String = "",
        var cid: String = ""
    ) {
        fun isValid(): Boolean {
            return byr.isNotBlank() &&
                    iyr.isNotBlank() &&
                    eyr.isNotBlank() &&
                    hgt.isNotBlank() &&
                    hcl.isNotBlank() &&
                    ecl.isNotBlank() &&
                    pid.isNotBlank()
        }

        fun isValidStrict(): Boolean {
            return isValid() &&
                    byr.toInt() in 1920..2002 &&
                    iyr.toInt() in 2010..2020 &&
                    eyr.toInt() in 2020..2030 &&
                    when(hgt.takeLast(2)) {
                        "cm" -> hgt.dropLast(2).toInt() in 150..193
                        "in" -> hgt.dropLast(2).toInt() in 59..76
                        else -> false
                    } &&
                    hcl.length == 7 && hcl.first() == '#' && hcl.removePrefix("#").filter { c -> Character.getNumericValue(c) in 0..9 || c in 'a'..'f'}.length == 6 &&
                    listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(ecl) &&
                    pid.length == 9 // check for 0 needed?

        }
    }

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
        val passport = Passport()

        passportString.trim().split(' ').map { element ->
            fun extract() = element.split(':').last()

            when (element.substring(0, 3)) {
                "byr" -> passport.byr = extract()
                "iyr" -> passport.iyr = extract()
                "eyr" -> passport.eyr = extract()
                "hgt" -> passport.hgt = extract()
                "hcl" -> passport.hcl = extract()
                "ecl" -> passport.ecl = extract()
                "pid" -> passport.pid = extract()
                "cid" -> passport.cid = extract()
                else -> throw IllegalArgumentException(extract())
            }
        }

        passports.add(passport)
    }

    override fun executePart1(): Day {
        println("Part 1: " + passports.filter { it.isValid() }.size)

        return this
    }

    override fun executePart2(): Day {
        println("Part 2: " + passports.filter { it.isValidStrict() }.size)

        return this
    }
}