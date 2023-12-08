/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2023/2024
 * Group Capstone Project
 * Group #7
 * 1 - 5026221169 - R. Farel Danendra Hendrawan Putra
 * 2 - 5026221095 - Nida Aulia Amartika
 * 3 - 5026221109 - Ahmad Fadhino Tegar Permana
 */
package sudoku.enums;

public enum CellStatus {
    GIVEN,         // clue, no need to guess
    TO_GUESS,      // need to guess - not attempted yet
    CORRECT_GUESS, // need to guess - correct guess
    WRONG_GUESS    // need to guess - wrong guess
    // The puzzle is solved if none of the cells have
    //  status of TO_GUESS or WRONG_GUESS
}
