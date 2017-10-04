package maze

import java.util.*

/**
 * Random Generator, especially for maze generation tasks
 *
 * @author Cedrik Kaufmann
 */
object MazeRandom : Random() {

    /**
     * Get a random direction
     *
     * @return random generated direction
     */
    fun nextDirection(): MazeDirection {
        val randDirection = this.nextInt(4)
        return MazeDirection.from(randDirection)
    }
}