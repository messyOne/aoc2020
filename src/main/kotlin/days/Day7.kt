package days

import java.io.File

class Day7 : Day {
    data class Bag(val color: String, val containingBags: Map<String, Int> = mapOf())

    private val bags: MutableList<Bag> = mutableListOf()

    init {
        parseData()
    }

    private fun parseData() {
        val bag = Regex("(\\S* \\S*) .* contain ")
        val contains = Regex("(\\d \\S* \\S*)")
        val empty = Regex("no other bags")

        File("src/main/resources/data7.txt").forEachLine { line ->
            val matchBag = bag.find(line)!!
            val color = matchBag.groupValues[1]
            val containingBags: MutableMap<String, Int> = mutableMapOf()

            if (!empty.containsMatchIn(line)) {
                val matchContains = contains.findAll(line)

                matchContains.forEach { s ->
                    val split = s.value.split(" ")

                    containingBags[split[1] + " " + split[2]] = split[0].toInt()
                }
            }

            bags.add(Bag(color, containingBags))
        }
    }

    override fun executePart1() {
        val lookingFor = "shiny gold"
        val found = mutableSetOf<Bag>()

        fun traverse(lookingFor: String) {
            this.bags.forEach { bag -> if (bag.containingBags.containsKey(lookingFor)) {
                found.add(bag)
//                println(bag.color + " contains " + lookingFor + ": $counter")
                traverse(bag.color)
            }}
        }

        traverse(lookingFor)

        println("Part 1: " + found.size)
    }

    override fun executePart2() {
        val lookingFor = "shiny gold"

        fun traverse(lookingFor: String): Int {
            val bag = this.bags.find { bag -> bag.color == lookingFor }!!

            return 1 + bag.containingBags.map { entry -> entry.value * traverse(entry.key) }.sum()
        }

        val total = traverse(lookingFor) - 1 // remove the shiny gold bag itself

        println("Part 2: $total")
    }
}