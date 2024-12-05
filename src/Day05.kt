fun main() {
    fun proc(input: List<String>, predicate: (a: List<Int>, b: List<Int>) -> Boolean): Int {
        var isS2 = false
        val orderings = mutableListOf<Pair<Int, Int>>()
        var sum = 0

        fun processUpdate(data: List<Int>) {
            val result = data.toMutableList()

            data.forEach { v ->
                orderings.forEach orderLoop@{ (a, b) ->
                    if (v != a) return@orderLoop
                    if (!data.contains(a) || !data.contains(b)) return@orderLoop

                    val valid = result.indexOf(a) < result.indexOf(b)
                    if (!valid) {
                        result.remove(a)
                        result.add(result.indexOf(b), a)
                    }
                }
            }

            if (predicate(result, data)) sum += result[result.size / 2]
        }

        input.forEach { line ->
            if (line.isEmpty()) {
                isS2 = true
                return@forEach
            }

            if (!isS2) {
                val (a, b) = line.split("|").map { it.toInt() }
                orderings.add(a to b)
            } else {
                processUpdate(line.split(",").map { it.toInt() })
            }
        }

        return sum
    }

    fun part1(input: List<String>) = proc(input) { a, b ->
        a == b
    }

    fun part2(input: List<String>): Int = proc(input) { a, b ->
        a != b
    }

    val testInput = """
        47|53
        97|13
        97|61
        97|47
        75|29
        61|13
        75|53
        29|13
        97|29
        53|29
        61|53
        97|53
        61|29
        47|13
        75|47
        97|75
        47|61
        75|61
        47|29
        75|13
        53|13

        75,47,61,53,29
        97,61,53,29,13
        75,29,13
        75,97,47,61,53
        61,13,29
        97,13,75,29,47
    """.trimIndent().split("\n")

    check(part1(testInput) == 143)
    check(part2(testInput) == 123)

    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}
