fun main() {
    val testInput = readInput("Day03_test")
    val input = readInput("Day03")

    check(part1(testInput) == 357L)
    part1(input).println()

    check(part2(testInput) == 3121910778619L)
    part2(input).println()
}

private fun part1(input: List<String>): Long =
    input.sumOf { bank ->
        bank
            .joltages()
            .takeMaxOnes(2)
            .output()
    }

private fun part2(input: List<String>): Long =
    input.sumOf { bank ->
        bank
            .joltages()
            .takeMaxOnes(12)
            .output()
    }

private fun Bank.joltages(): Joltages =
    toCharArray().map { it.digitToInt() }

private fun Joltages.takeMaxOnes(count: Int): Joltages =
    if (count == 0) {
        emptyList()
    } else {
        val safeToSelect = dropLast(count - 1)
        val max = safeToSelect.max()
        val i = indexOfFirst { it == max }
        listOf(max) + drop(i + 1).takeMaxOnes(count - 1)
    }

private fun Joltages.output(): Long =
    joinToString(separator = "") { it.toString() }.toLong()

typealias Bank = String

typealias Joltages = List<Int>
