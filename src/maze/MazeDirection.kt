package maze

/**
 * Enumeration for basic directions
 *
 * @author Cedrik Kaufmann
 */
enum class MazeDirection(val value: Int) {
    Up(0), Down(1), Left(2), Right(3);

    companion object {

        /**
         * Get enum from given value
         *
         * @param value Value to get enum from
         */
        fun from(value: Int): MazeDirection {
            return when (value) {
                0 -> Up
                1 -> Down
                2 -> Left
                3 -> Right
                else -> {
                    throw IndexOutOfBoundsException("No assignment for given value: $value")
                }
            }
        }
    }
}