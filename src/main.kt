import maze.MazeDFS

fun main(args: Array<String>) {
    // Set default maze size
    var height = 21
    var width = 21

    // Read maze size from given args
    if (args.size == 2) {
        height = args[0].toInt()
        width = args[1].toInt()
    }

    // Generate maze
    val maze = MazeDFS.generate(height, width)

    // Output maze on std output
    for (y in (0..(maze.raw.size-1))) {
        for (x in (0..(maze.raw[0].size-1))) {
            print("${maze.raw[y][x].value} ")
        }
        print("\n")
    }
}