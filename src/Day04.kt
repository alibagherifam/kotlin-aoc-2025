fun main() {
    val testInput = readInput("Day04_test")
    val input = readInput("Day04")

    check(part1(testInput) == 13)
    part1(input).println()

    check(part2(testInput) == -1)
    part2(input).println()
}

private fun part1(lines: List<String>): Int {
    val grid = gridOfRolls(lines)
    var count = 0

    grid.forEachRoll { row, col ->
        val adj = grid.countAdjacentRollsOf(row, col)
        if (adj < 4) {
            count++
        }
    }

    return count
}

private fun part2(lines: List<String>): Int = -1

fun Grid.forEachRoll(action: (row: Int, col: Int) -> Unit) {
    for (row in indices) {
        for (col in this[row].indices) {
            if (this[row][col]) {
                action(row, col)
            }
        }
    }
}

fun Grid.countAdjacentRollsOf(row: Int, col: Int): Int =
    adjacentPositionsOf(row, col).count { it }

fun Grid.adjacentPositionsOf(row: Int, col: Int): List<Boolean> =
    buildList {
        for (r in row - 1..row + 1) {
            for (c in col - 1..col + 1) {
                if (r == row && c == col) {
                    continue
                }

                val grid = this@adjacentPositionsOf
                if (r in grid.indices && c in grid[r].indices) {
                    add(grid[r][c])
                }
            }
        }
    }

fun gridOfRolls(lines: List<String>): Grid =
    lines.map { line ->
        line.map { char -> char == ROLL }
    }

typealias Grid = List<List<Boolean>>

const val ROLL = '@'
