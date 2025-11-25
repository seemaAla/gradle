package ie.tudublin

class Sudoku(rows: List<String>) {

    private val grid = IntArray(81)
    private var iterations: Long = 0
    private val iterationLimit: Long = 2_000_000

    var solved: Boolean = false
        private set

    init {
        require(rows.size == 9 && rows.all { it.length == 9 }) {
            "Grid must be 9 x 9"
        }
        for (i in 0..8) {
            for (j in 0..8) {
                grid[9 * i + j] = rows[i][j] - '0'
            }
        }
    }

    fun solve(): Boolean {
        iterations = 0
        solved = false
        placeNumber(0)
        return solved
    }

    private fun placeNumber(pos: Int) {
        // Stop if already solved or limit reached
        if (solved || iterations >= iterationLimit) return

        iterations++
        if (iterations >= iterationLimit) {
            //give up silently and let solve() return false
            return
        }

        if (pos == 81) {
            solved = true
            return
        }

        if (grid[pos] != 0) {
            placeNumber(pos + 1)
            return
        }

        val x = pos % 9
        val y = pos / 9

        for (n in 1..9) {
            if (checkValidity(n, x, y)) {
                grid[pos] = n
                placeNumber(pos + 1)
                if (solved) return
                grid[pos] = 0
            }
        }
    }

    private fun checkValidity(v: Int, x: Int, y: Int): Boolean {
        for (i in 0..8) {
            if (grid[y * 9 + i] == v) return false    // row
            if (grid[i * 9 + x] == v) return false    // column
        }

        val startX = (x / 3) * 3
        val startY = (y / 3) * 3

        for (i in startY until startY + 3) {
            for (j in startX until startX + 3) {
                if (grid[i * 9 + j] == v) return false
            }
        }

        return true
    }

    fun boardToString(): String {
        val sb = StringBuilder()
        for (i in 0..8) {
            for (j in 0..8) {
                sb.append(grid[i * 9 + j]).append(" ")
                if (j == 2 || j == 5) sb.append("| ")
            }
            sb.append("\n")
            if (i == 2 || i == 5) sb.append("------+-------+------\n")
        }
        return sb.toString()
    }
}
