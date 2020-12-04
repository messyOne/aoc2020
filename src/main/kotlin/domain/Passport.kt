package domain

import java.lang.IllegalArgumentException

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
    companion object Factory {
        fun create(passportString: String): Passport {
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

            return passport
        }
    }

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
                hcl.isHexadecimal() &&
                listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(ecl) &&
                pid.length == 9 // check for 0 needed?
    }
}

