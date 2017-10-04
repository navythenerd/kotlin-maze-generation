package maze

/**
 * Interface provides simple maze generation
 *
 * @author Cedrik Kaufmann
 */
interface MazeGenerator {

    /**
     * Generate a maze
     *
     * @param height Height of maze
     * @param width Width of maze
     * @return Randomly generated maze
     */
    fun generate(height: Int, width: Int): Maze
}