fun main() {
    val test = ""
    val di = listOf(-1, 0, 1, 0);
    val dj = listOf(0, 1, 0, -1);
    val mat = test.split("\n");
    val n = mat.size;
    val m = mat[0].length;
    val best = Array(n) {Array(m) {n * m} };
    val next: MutableList<Pair<Int, Int>> = mutableListOf();
    for((i, s) in mat.withIndex()) {
        for(j in 0 until s.length) {
            // remove the 'a' clause for first subproblem
            if (mat[i][j] == 'S' || mat[i][j] == 'a') {
                next.add(Pair(i, j));
                best[i][j] = 0;
            }
        }
    }

    var curInd = 0;
    while(curInd < next.size) {
        val cur = next[curInd++];
        for(d in 0..3) {
            val newPos = Pair(cur.first + di[d], cur.second + dj[d]);
            if (newPos.first < 0 || newPos.first >= n || newPos.second < 0 || newPos.second >= m) continue;
            val curHeight = if (mat[cur.first][cur.second] == 'S') 0 else mat[cur.first][cur.second] - 'a';
            val newHeight = if (mat[newPos.first][newPos.second] == 'E') 25 else mat[newPos.first][newPos.second] - 'a';
            if (curHeight + 1 >= newHeight &&
                best[newPos.first][newPos.second] > best[cur.first][cur.second] + 1) {
                best[newPos.first][newPos.second] = best[cur.first][cur.second] + 1;
                next.add(newPos);
            }
        }
    }
    for((i, s) in mat.withIndex()) {
        for(j in 0 until s.length) {
            if (mat[i][j] == 'E') {
                println(best[i][j]);
            }
        }
    }

}
