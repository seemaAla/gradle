package ie.tudublin

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertThrows


class SudokuTest {
    
    @Test
    fun testValidBoard() {
        val board = List(9) { "000000000" } // empty board
        val sudoku = Sudoku(board)
        assertTrue(sudoku.solve())
    }

    @Test
    fun testRealExample() {
        val board = listOf(
            "530070000",
            "600195000",
            "098000060",
            "800060003",
            "400803001",
            "700020006",
            "060000280",
            "000419005",
            "000080079"
        )
        val sudoku = Sudoku(board)
        assertTrue(sudoku.solve())
    }

    
    @Test
    fun testUnsolvableBoard() {
        val board = listOf(
            "530070000",
            "600195000",
            "098000060",
            "800060003",
            "400803001",
            "700020006",
            "060000280",
            "000419005",
            "000080071" 
        )
        val sudoku = Sudoku(board)
        assertFalse(sudoku.solve())
    }


    //invalid test handled by exception
    @Test
    fun testInvalidInput() {
        assertThrows(IllegalArgumentException::class.java) {
            //invalid board as its not 9x9
            Sudoku(listOf("530070000")) 
        }
    }


}
