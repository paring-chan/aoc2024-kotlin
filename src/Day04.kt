fun main() {
    fun part1(input: List<String>): Int {
        val h = input.size
        val w = input[0].length

        fun c(x: Int, y: Int): Char {
            if (y >= h || y < 0) return '.'
            if (x >= w || x < 0) return '.'
            return input[y][x]
        }

        fun search(x: Int, y: Int): Int {
            var result = 0

            fun test(dirX: Int, dirY: Int) {
                val s = "${c(x, y)}${c(x + dirX, y + dirY)}${c(x + (dirX * 2), y + (dirY * 2))}${
                    c(
                        x + (dirX * 3),
                        y + (dirY * 3)
                    )
                }"
                if (s == "XMAS") {
                    result++
                }
            }

            test(1, 0)
            test(-1, 0)
            test(0, 1)
            test(0, -1)

            // left top
            test(-1, 1)

            // right top
            test(1, 1)

            // left bottom
            test(-1, -1)

            // right bottom
            test(1, -1)

            return result
        }

        var sum = 0

        for (x in 0 until w) {
            for (y in 0 until h) {
                sum += search(x, y)
            }
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        val h = input.size
        val w = input[0].length

        fun c(x: Int, y: Int): Char {
            if (y >= h || y < 0) return '.'
            if (x >= w || x < 0) return '.'
            return input[y][x]
        }

        fun search(x: Int, y: Int): Boolean {
            fun test(dirX: Int, dirY: Int): Boolean {
                val s = "${c(x + (dirX * -1), y + (dirY * -1))}${c(x, y)}${c(x + dirX, y + dirY)}"
                return s == "MAS" || s == "SAM"
            }

            return test(-1, -1) && test(1, -1)
        }

        var sum = 0

        for (x in 0 until w) {
            for (y in 0 until h) {
                if (c(x, y) != 'A') continue
                if (search(x, y)) {
                    sum++
                }
            }
        }

        return sum
    }

    val testInput = """
        MMMSXXMASM
        MSAMXMSMSA
        AMXSXMAAMM
        MSAMASMSMX
        XMASAMXAMM
        XXAMMXXAMA
        SMSMSASXSS
        SAXAMASAAA
        MAMMMXMMMM
        MXMXAXMASX
    """.trimIndent().split("\n")

    check(part1(testInput) == 18)
    check(part2(testInput) == 9)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
