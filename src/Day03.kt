fun main() {
    data class Switch(val position: Int, val on: Boolean)

    fun part1(input: List<String>): Int =
        input.sumOf { exp ->
            val items = Regex("mul\\((\\d+),(\\d+)\\)").findAll(exp)
            items.sumOf {
                (it.groupValues[1].toIntOrNull() ?: 0) * (it.groupValues[2].toIntOrNull() ?: 0)
            }
        }

    fun part2(input: List<String>): Int =
        input.joinToString("\n").let{ exp ->
            val switches =
                listOf(Switch(0, true)) +
                        Regex("do(n't)?\\(\\)").findAll(exp)
                            .map { Switch(it.range.first, it.groupValues[1].isEmpty()) }
                            .toList().sortedBy { it.position }

            val items = Regex("mul\\((\\d+),(\\d+)\\)").findAll(exp)
            items.filter { switches.findLast { sw -> sw.position < it.range.first }?.on ?: false }
                .sumOf {
                    (it.groupValues[1].toIntOrNull() ?: 0) * (it.groupValues[2].toIntOrNull() ?: 0)
                }
        }

    check(part1(listOf("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))")) == 161)
    check(part2(listOf("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))")) == 48)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
