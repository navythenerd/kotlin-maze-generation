package maze

/**
 * Enumeration for maze fields
 *
 * @author Cedrik Kaufmann
 */
enum class MazeField(val value: Char) {
    Free('.'), Blocked('#');
}