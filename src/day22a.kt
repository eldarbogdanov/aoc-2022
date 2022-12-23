fun main() {
    val board = """        ...#
        .#..
        #...
        ....
...#.......#
........#...
..#....#....
..........#.
        ...#....
        .....#..
        .#......
        ......#.""".split("\n")
    val instructions = "10R5L5R10L4R5L5"

    val di = listOf(0, 1, 0, -1)
    val dj = listOf(1, 0, -1, 0)

    var row = 0
    var col = 0
    var dir = 0
    for((index, c) in board[0].withIndex()) {
        if (c == '.') {
            col = index
            break
        }
    }

    val n = board.size
    val m = board[0].length
    var num = 0
    for(c in instructions + "#") {
        if (c == 'L' || c == 'R' || c == '#') {
            for(x in 1..num) {
                val newRow = row + di[dir]
                val newCol = col + dj[dir]
                if (newRow < 0 || newCol < 0 || newRow >= n || newCol >= m || board[newRow].length <= newCol || board[newRow][newCol] == ' ') {
                    var searchRow = when(dir) {
                        0, 2 -> row
                        1 -> 0
                        3 -> n - 1
                        else -> throw Exception()
                    }
                    var searchCol = when(dir) {
                        1, 3 -> col
                        0 -> 0
                        2 -> m - 1
                        else -> throw Exception()
                    }
                    while(board[searchRow].length <= searchCol || board[searchRow][searchCol] == ' ') {
                        searchRow += di[dir]
                        searchCol += dj[dir]
                    }
                    if (board[searchRow][searchCol] == '#') break
                    row = searchRow
                    col = searchCol
                } else {
                    if (board[newRow][newCol] == '#') break
                    row = newRow
                    col = newCol
                }
            }
            num = 0
            if (c == 'L') dir = (dir + 3) % 4
            else if (c == 'R') dir = (dir + 1) % 4
        } else {
            num = num * 10 + (c - '0')
        }
    }
    println(1000 * (row + 1) + (col + 1) * 4 + dir)
}
