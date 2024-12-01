import kotlin.math.absoluteValue

fun main() {
    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}

fun getInput(input: List<String>): List<List<Int>> {
    val a = mutableListOf<Int>()
    val b = mutableListOf<Int>()
    input.forEach { s ->
        val (v1, v2) = s.split("   ").map { it.toInt() }
        a += v1
        b += v2
    }

    return listOf(a, b)
}

fun part1(input: List<String>): Int =
    getInput(input).map { it.sorted() }.let { (a, b) ->
        a.mapIndexed { i, x -> (x - b[i]).absoluteValue }.sum()
    }


fun part2(input: List<String>): Int =
    getInput(input).let { (a, b) ->
        a.sumOf { it * b.count { x -> it == x } }
    }

