fun main() {
    val input = ""
	var ans = 0;
    val mat = input.split("\n");
    val di = listOf(-1, 0, 1, 0);
    val dj = listOf(0, 1, 0, -1);
    for((i, s) in mat.withIndex()) {
        for(j in 0 until s.length) {
            var score = 1;
            for(d in 0..3) {
                var ii = i;
                var jj = j;
                for(k in 1..1000) {
                    ii += di[d];
                    jj += dj[d];
                    if (ii < 0 || jj < 0 || ii >= mat.size || jj >= mat[0].length) {
//                        println(i.toString() + " " + j.toString());
//		                println(k);
                        score *= k - 1;
                        break;
                    }
                    if (mat[ii][jj] >= mat[i][j]) {
//                        println(i.toString() + " " + j.toString());
//                        println(k);
                        score *= k;
                        break;
                    }
                }
            }
            ans = Math.max(ans, score);
        }
    }
    println(ans);
}

