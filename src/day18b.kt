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
    val seen = mutableSetOf<Triple<Int, Int, Int>>();

    for(cube in test.split("\n")) {
        val (x, y, z) = cube.split(",").map { Integer.parseInt(it) };
        cubes.add(Triple(x, y, z));
    }

    val next = mutableListOf<Triple<Int, Int, Int>>();
    next.add(Triple(-1, -1, -1));
    var it = 0;
    while(it < next.size) {
        val (x, y, z) = next[it];
        ++it;
        for(di in -1..1) {
            for (dj in -1..1) {
                for (dk in -1..1) {
                    if (abs(di) + abs(dj) + abs(dk) != 1) continue;
                    val xx = x + di
                    val yy = y + dj
                    val zz = z + dk
                    if (xx < -1 || yy < -1 || zz < -1 || xx > 22 || yy > 22 || zz > 22) continue;
                    if (cubes.contains(Triple(xx, yy, zz)) || seen.contains(Triple(xx, yy, zz))) continue;
                    next.add(Triple(xx, yy, zz))
                    seen.add(Triple(xx, yy, zz))
                }
            }
        }
    }

    var ans = 0;
    for(cube in cubes) {
        val (x, y, z) = cube
        for(di in -1..1) {
            for(dj in -1..1) {
                for(dk in -1..1) {
                    if (abs(di) + abs(dj) + abs(dk) != 1) continue;
                    val xx = x + di
                    val yy = y + dj
                    val zz = z + dk
                    if (!cubes.contains(Triple(xx, yy, zz)) && seen.contains(Triple(xx, yy, zz))) {
                        ++ans;
                    }
                }
            }
        }
    }
    println(ans);
}
