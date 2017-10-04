package maze

/**
 * Singleton object for simple maze generation based on depth-first-search backtracking
 *
 * @author Cedrik Kaufmann
 */
object MazeDFS : MazeGenerator {

    private var rawMaze: Array<Array<MazeField>> = emptyArray() // maze data
    private var backtrackState: Array<Array<BacktrackState>> = emptyArray() // state array used for backtracking

    /**
     * Generate a random maze
     *
     * @param height Height of maze
     * @param width Width of maze
     * @return Randomly generated maze
     */
    override fun generate(height: Int, width: Int): Maze {
        // Working boundary check
        if (height < 4 && width < 4) throw MazeGenerationException("Height and width has to be greater than 4")
        if (height %2 == 0 || width %2 == 0) throw MazeGenerationException("Size of maze has to be odd numbers")

        // Initialize raw maze
        rawMaze = Array(height, { _ -> Array(
                width, { _ -> MazeField.Blocked}
        )})

        for (y in 1..(height - 2) step (2)) {
            for (x in 1..(width - 2) step (2)) {
                rawMaze[y][x] = MazeField.Free
            }
        }

        // Looking for random start position
        var startX: Int
        var startY: Int

        do {
            startX = MazeRandom.nextInt(width)
            startY = MazeRandom.nextInt(height)
        } while (rawMaze[startY][startX] != MazeField.Free)

        // Initialize state array for dfs-backtracking
        backtrackState = Array(height, { _ -> Array(
                width, { _ -> BacktrackState.Unvisited}
        )})

        // Find path through maze by dfs-backtracking
        findPath(startY, startX)

        // Return finished maze
        return Maze(rawMaze)
    }

    /**
     * Internal dfs method, to find valid path
     */
    private fun findPath(y: Int, x: Int) {
        val triedDirections = Array(4, { _ -> false })

        backtrackState[y][x] = BacktrackState.Visited

        while (!triedDirections[0] || !triedDirections[1] || !triedDirections[2] || !triedDirections[3]) {
            var direction = MazeRandom.nextDirection()

            while (triedDirections[direction.value]) {
                direction = MazeRandom.nextDirection()
            }

            triedDirections[direction.value] = true

            when (direction) {

                MazeDirection.Up -> {
                    if (y > 2 && backtrackState[y-2][x] == BacktrackState.Unvisited) {
                        rawMaze[y-1][x] = MazeField.Free
                        findPath(y-2, x)
                    }
                }

                MazeDirection.Down -> {
                    if (y < (rawMaze.size - 3) && backtrackState[y+2][x] == BacktrackState.Unvisited) {
                        rawMaze[y+1][x] = MazeField.Free
                        findPath(y+2, x)
                    }
                }

                MazeDirection.Left -> {
                    if (x > 2 && backtrackState[y][x-2] == BacktrackState.Unvisited) {
                        rawMaze[y][x-1] = MazeField.Free
                        findPath(y, x-2)
                    }
                }

                MazeDirection.Right -> {
                    if (x < (rawMaze[0].size - 3) && backtrackState[y][x+2] == BacktrackState.Unvisited) {
                        rawMaze[y][x+1] = MazeField.Free
                        findPath(y, x+2)
                    }
                }
            }
        }

        return
    }
}