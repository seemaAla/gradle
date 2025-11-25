package ie.tudublin

import java.io.File

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Usage: kotlin AppKt <input-file>")
        return
    }

    val filename = args[0]
    val lines = try {
        File(filename).readLines()
    } catch (e: Exception) {
        println("Could not read file: $filename")
        return
    }

    if (lines.size != 9 || lines.any { it.length != 9 }) {
        println("Input file must contain 9 rows of 9 digits each")
        return
    }

    val sudoku = Sudoku(lines)
    println("Input board:\n")
    println(sudoku.boardToString())

    val solved = sudoku.solve()
    if (solved) {
        println("Solved board:\n")
        println(sudoku.boardToString())
    } else {
        println("Could not find a solution (either unsolvable or exceeded iteration limit)")
    }
}
