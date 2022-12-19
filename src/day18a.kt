import kotlin.math.abs

fun main() {
    val test = """2,2,2
1,2,2
3,2,2
2,1,2
2,3,2
2,2,1
2,2,3
2,2,4
2,2,6
1,2,5
3,2,5
2,1,5
2,3,5"""

    val cubes = mutableSetOf<Triple<Int, Int, Int>>();
    var ans = 0;
    for(cube in test.split("\n")) {
        val (x, y, z) = cube.split(",").map { Integer.parseInt(it) };
        ans += 6;
        for(di in -1..1) {
            for(dj in -1..1) {
                for(dk in -1..1) {
                    if (abs(di) + abs(dj) + abs(dk) != 1) continue;
                    val xx = x + di
                    val yy = y + dj
                    val zz = z + dk
                    if (cubes.contains(Triple(xx, yy, zz))) {
                        ans -= 2;
                    }
                }
            }
        }
        cubes.add(Triple(x, y, z));
    }
    println(ans);
}
