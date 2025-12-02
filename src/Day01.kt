fun main() {
    val testInput = readInput("Day01_test")
    val input = readInput("Day01")

    check(part1(testInput) == 3)
    part1(input).println()

    check(part2(testInput) == 6)
    part2(input).println()
}

private fun part1(input: List<String>): Int {
    var result = 0
    input.fold(50) { currentValue, line ->
        (currentValue + line.value()).mod(100).also {
            if (it == 0) {
                result++
            }
        }
    }
    return result
}

private fun part2(input: List<String>): Int {
    var result = 0
    input.fold(50) { currentValue, line ->
        val inc = line.value()
        (currentValue + inc).mod(100).also {
            result += if (inc > 0) {
                (currentValue + inc) / 100
            } else {
                ((100 - currentValue) % 100 - inc) / 100
            }
        }
    }
    return result
}

private fun String.value(): Int = signed().toInt()

private fun String.signed(): String = replace('R', '+').replace('L', '-')
