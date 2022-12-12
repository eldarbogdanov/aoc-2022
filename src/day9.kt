val di = listOf(-1, 0, 1, 0);
val dj = listOf(0, 1, 0, -1);

val dirMap: Map<String, Int> = mapOf("U" to 0, "R" to 1, "D" to 2, "L" to 3);

fun dist(a: Pair<Int, Int>, b: Pair<Int, Int>): Int {
    return Math.max(Math.abs(a.first - b.first), Math.abs(a.second - b.second));
}

fun dist2(a: Pair<Int, Int>, b: Pair<Int, Int>): Int {
    return Math.abs(a.first - b.first) + Math.abs(a.second - b.second);
}

fun newPos(oldPos: Pair<Int, Int>, leader: Pair<Int, Int>, dir: Int): Pair<Int, Int> {
    if (dist(oldPos, leader) <= 1) {
        return oldPos;
    }

    if (dir != -1) {
        val candidate = Pair(oldPos.first + di[dir], oldPos.second + dj[dir]);
        if (dist2(leader, candidate) == 1) {
            return candidate;
        }
    }
            
    var best = oldPos;
    for(dii in -1..1) {
        for(djj in -1..1) {
            val candidate = Pair(oldPos.first + dii, oldPos.second + djj);
            if (dist2(leader, candidate) == 1) {
                best = candidate;
            }
        }
    }
    if (dist2(leader, best) == 1) return best;

    for(dii in -1..1) {
        for(djj in -1..1) {
            val candidate = Pair(oldPos.first + dii, oldPos.second + djj);
            if (dist(leader, candidate) == 1) {
                best = candidate;
            }
        }
    }
    return best;
}

fun main() {
    val input = ""
    val st: MutableSet<Pair<Int, Int>> = mutableSetOf(Pair(0, 0));
    var snake: MutableList<Pair<Int, Int>> = mutableListOf();
    val knots = 10;
    for(i in 1..knots) {
        snake.add(Pair(0, 0));
    }

    for(s in input.split("\n")) {
        val dir = dirMap[s.split(" ")[0]]!!;
        val num = Integer.parseInt(s.split(" ")[1]);
        for(k in 1..num) {
	        val newSnake: MutableList<Pair<Int, Int>> = mutableListOf();
            newSnake.add(Pair(snake[0].first + di[dir], snake[0].second + dj[dir]));
            for(knot in 1 until knots) {
                newSnake.add(newPos(snake[knot], newSnake[knot - 1], dir));
            }
			st.add(newSnake[knots - 1]);
            snake = newSnake;
            //println(snake);
        }
    }
    println(st.size);
}

