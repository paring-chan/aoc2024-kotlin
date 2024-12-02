fun main() {
    fun check(items: List<Int>): Boolean {
        var result = true
        var prev = items[0]
        val direction = if (items[0] > items[1]) -1 else 1

        items.slice(1..<items.size).forEach {
            if (
                it != prev + direction &&
                it != prev + (direction * 2) &&
                it != prev + (direction * 3)
            ) {
                result = false
                return@forEach
            }

            prev = it
        }

        return result
    }

    fun part1(input: List<String>): Int =
        input.count { line ->
            val items = line.split(" ").map { it.toInt() }
            check(items)
        }

    fun part2(input: List<String>): Int =
        input.count { line ->
            val initialItems = line.split(" ").map { it.toInt() }

            if (check(initialItems)) return@count true

            List(initialItems.size) { index ->
                val items = initialItems.toMutableList()
                items.removeAt(index)
                check(items)
            }.contains(true)
        }

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
