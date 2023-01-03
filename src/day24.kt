fun main() {
    val test = """#.######################################################################################################################################################
#.v><><<>^^^<><<>.^v<>v^>v^v^^<..^v>^>^vv.>v^<<>^.v<vvv<.>><v>><^>><>^<><v<>>>><v>v<^<>^.v..v^vv<v<<vv.v>>v<>v.<..^<vv<^v^v.v>>v>><^v>^<.vv^^<<><<>v>><#
#.vv<vv<>.v<<<<<>>vv<>^v^^<^<>><.<^>^<^>^<v<<<<v>v>><.><.^><>vv<^^><<<<>^<.<v^.vv^vv>v>^.<<<><v<.v.<><<v^^v>^^<^>>^.<v^v.v><<v^vv<<v>^v>><^^<^<v>^>><v<#
#<v^>v^vv<><v><<^<>^<^vv>v>>^^><<<vv><v.v.^><<^><>v>^<^v<<^>v<vv><v<<^.vv^>>>.^v>v.><v>v<^<^^<><<>><<^^^>v<^>>^v>>vv.<>^<><<^v>><^.>v^v><<^^^^<v<>^v<v.#
#<<^<vv^<^><>^><>^vv.v^v>><^^<>^<>>^><^.^^<.v...>^^.^<<v<vvv>>>v^..^v.v<^><>.>v^.vv<^^vv>vv^.^.vv<v>>^>v.^^^<v<>><vv^>>^^.v>.v^^<^>vvv^<<v^<v<^><^>v<>>#
#<.<^>>^<v><^>^^.>>v^<v^vvv>>vv.v^<>^^>v<<vv^<>^v.<<<^>>>.^>vvv.^^^<^><<^><v>><^.^>vv^^vv.><<v<^<v^^^.>v>^>v.^^<<^^^>^><><<vv<.<>v>vv>><^^v>>vvv^<...<<#
#><v.v>vv>^^^>>><v>^^v>^>>><vv^<^<^<<<>.<<.<.<..><><v.v^^.v..v^>>>^v><.^^><<>v^<^<>^>>.v^v.v.>v>^>>>v>>^>><^<.v.v<^<<<<<^^>^^^^>^^<v^>>v><>.<>v><>.>><>#
#<><<.>^<>^^v<v>^><^>v^.>><vv^vv^<v<<>.>.>>.vv.v<.^<^<>>^><><^.v>>v^^v><>><<<vv<<>v><>^.>>^>v^^><^<v<<><vv^>>.<^<.^<^>vvv>^<.v<^<>.><.>>v<^>^v<<.^<v>^<#
#<>.<v^>.^<<^v^.<<^>v^^vv<^><^<^>><v^^vvv<<vvvv>>^v><<>^>vvvvv^<<v^.>^<.<^>.^>^><v^<>>.<<^^vv>>>^>vv><vvv^v.>^vv><<<<>^v^^^.>.^>>^^v^v>v>vv^<<^vv^<>v<>#
#<<>v>v>>v^^v><^<.vv<^>^^>.v^<.^^vv>.<>^<>v>^>vvv<vv^>v^><<<v>vv<..^v^v>v.v><^.<^>.v<v><v>v^>^<<<<<>^.v<^.<v.<<^>v^^^<^<<>v.<<^<<<<.<^><<<^<v<v>>v>.><>#
#>>.>vvv<<..>^^vv^>.<.v..v^^>^v<<>>>vv.<v>vv^<vv^v><v^^.<<.><..><^><<>^<<><v<.<v.<^<^.^<^<<><><<^v^>.<<v^^>.^<...v.^<^^<><><>v^<><^>^>^v^.>^^>^.vv.<v^>#
#>.<><v>v<v>v>^v^^v>.v^.v>v>.<>>>^vv<<v>>^>^.^<^vv.vvvv<<>v<^^.>v<^v<<^><^.^^v<>.v<vv><v<v>vvv>^^>v>..<<<v..>v..<^^^<v>^^<<vv<>>^^v^.vv<><^>>.^<<^>>v.>#
#><v..vv^<v^<>>v<>..<^>v^^><.>vv^vv^<^^v<>vv<.vv^^<>^>>^v<^^<<>^vv>v><>v<^vvv^<>><>^^^v^<v.v^<<^^>>>>><^^>><^v>>vv^v><^>^<<^.v>vv>^.v^<vv^v>>>vv>v^v<^>#
#...>><v.<v<>^.v.v..^>^<><vv^><<<vv^<vv.><^.vv>>><v^v^>^v>>v<.^<^^<v^<>v<^<><<<.^<<vv.<>>^v^^.v>.<v>^.^>v^.v><^<<>>>^.<<<^v<<<><^>.^.^v><>^>><vv<v.<.^>#
#<^vv>>v>v<^><^^^<^>vv<>vv>^^^.><><^>v>><v.<>vv^^<^<<<v^<><v>v^v>^<><><><><>^^>><>..^^>v<...vv<^>><.vv<^<v><v<vv^.><>v^<>v.^>>>v<<^<<^vv.>^<.^<>^<v^^^>#
#>>^vv^.<<.<.<>>vvv<<<..<v><^<>^><<>^.<<^^^<v^<<v.<vv<^<>>vv^>.v^v<^<>>vv<<^>>.v>.v>^^^vvv^v<^><v^><>><^<.>v^^^<vv^>^v<v^<vv><>..^><.>^^<^>>..^.v^<^<v>#
#<<><^^><v^v<^^<vv>><v..>>>^><>>>v>v>v>v><>>^vv><>>v>>vvvv<<^><>v^>vv>^.>>v.^<v<^.>>>^^v>>><vvv>><.<v<^<v<^v<v<<vvv..^^<<^<.<><<<<^<>v<<<^^>.^v>^^v<^^<#
#>v^^^<v>^^<v<<<vv><^v.v<v^^^vvv<<><><^^><^^>>>>>><^>^^.>vv>^><<<v<<<^vv.^^^<v>v<^^^^>.^^vv<^<.<v^<vv^^^>><..><^>v><<v>v>^v^>>^^>vv.v^^^>^^vv.<<v.^><<.#
#<^v<<^^vvv.>v>v>.v><v<^^>^>^>^^v>v^vv^<.^.>>>>>v<>.<<.<^<<vvv..<^>^><^>^<^<<>><^>v<v^v>^v^^^>>>>v>^>>^<v..><.>>^^><^<^.<v^>^.v>^^vv^v.<>>>>.v.<<>^^vv>#
#<v^v.>.>^v>^<v.<^<^>v^>^^.>.>v<>>>^>vv<>v<>^>v^<<^<.<<^>v^<><>><^^<^^v<>>vv<<.<>v>.>^^^>><^^<^vv.><v<.<<^^>^>><>v><^vv.>v^v^v..<vvv..<<v<v^<<v>^<^>><<#
#<^v<v^^v^>^<>v^<^<>>..>><>v<.^<.>v.>^v.>>^^<^v>>v^.v>^><>>vv.<<<>v>^.>v>..<<<v.<v^<>^>^<>v<^<v<><^>^v><.<<^^^<^.<<<^.^v^^.>^.<<vv>^v><<>^<^v^^>^v><<^>#
######################################################################################################################################################.#""".split("\n")
    val n = test.size
    val m = test[0].length
    var can = Array(n) { Array(m) { Array(3) { false } } }
    can[0][1][0] = true
    var blizzard = Array(n) { Array(m) { Array(4) { false } } }
    for((i, row) in test.withIndex()) {
        for((j, c) in row.withIndex()) {
            if (c == '^') blizzard[i][j][0] = true
            if (c == '>') blizzard[i][j][1] = true
            if (c == 'v') blizzard[i][j][2] = true
            if (c == '<') blizzard[i][j][3] = true
        }
    }
    val di = listOf(-1, 0, 1, 0, 0)
    val dj = listOf(0, 1, 0, -1, 0)
    for(move in 1..100000) {
        val newBlizzard = Array(n) { Array(m) { Array(4) { false } } }
        for (i in 0 until n) {
            for (j in 0 until m) {
                for (dir in 0..3) {
                    if (!blizzard[i][j][dir]) continue
                    val ii = i + di[dir]
                    val jj = j + dj[dir]
                    if (test[ii][jj] == '#') {
                        if (dir == 0) newBlizzard[n - 2][j][0] = true
                        if (dir == 1) newBlizzard[i][1][1] = true
                        if (dir == 2) newBlizzard[1][j][2] = true
                        if (dir == 3) newBlizzard[i][m - 2][3] = true
                    } else {
                        newBlizzard[ii][jj][dir] = true
                    }
                }
            }
        }
        val newCan = Array(n) { Array(m) { Array(3) { false } } }
        for (i in 0 until n) {
            for (j in 0 until m) {
                for(k in 0..2) {
                    if (can[i][j][k]) {
                        for (dir in 0..4) {
                            val ii = i + di[dir]
                            val jj = j + dj[dir]
                            if (ii < 0 || jj < 0 || ii >= n || jj >= m || test[ii][jj] == '#') continue
                            if (newBlizzard[ii][jj].contains(true)) continue
                            newCan[ii][jj][k] = true
                            if (k == 0 && ii == n - 1 && jj == m - 2) {
                                newCan[ii][jj][1] = true
                            }
                            if (k == 1 && ii == 0 && jj == 1) {
                                newCan[ii][jj][2] = true
                            }
                        }
                    }
                }
            }
        }
        can = newCan
        blizzard = newBlizzard

        if (can[n - 1][m - 2][2]) {
            println(move)
            return
        }
    }

}
