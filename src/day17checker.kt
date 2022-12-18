import kotlin.random.Random

fun cycleLen(test: String): Int {

    val cave = Array(10000000) { Array(7) { false } }
    var maxH = 0

    val blocks = listOf(
        listOf("####"),
        listOf(".#.", "###", ".#."),
        listOf("###", "..#", "..#"),
        listOf("#", "#", "#", "#"),
        listOf("##", "##")
    )

    data class State(val cave: String, val blockIndex: Int, val jetIndex: Int)

    val stateToIndex: MutableMap<State, Int> = mutableMapOf();
    val stateToHeight: MutableMap<State, Int> = mutableMapOf();

    fun can(block: List<String>, x: Int, y: Int): Boolean {
        if (x < 0 || y < 0 || x + block[0].length > 7) return false;
        for(i in 0 until 4) {
            if (block.size <= i) break;
            for(j in 0 until block[i].length) {
                if (block[i][j] == '#') {
                    if (cave[y + i][x + j]) return false;
                    if (x + j >= 7) return false;
                }
            }
        }
        return true;
    }

    val surfaces: MutableSet<String> = mutableSetOf();

    fun draw() {
        for(i in 0 until maxH) {
            for(j in 0..6) {
                if (cave[i][j]) print("#") else print(".");
            }
            println();
        }
        println();
        println();
    }

    var index = 0;
    val END = 1000000000000L;
    var left = END;
    var add = 0L;
    for(blockIndex in 0 until 100000000) {
        val block = blocks[blockIndex % 5];
        if (left == 0L) break;
        left -= 1;
        var x = 2;
        var y = maxH + 3;
        while(true) {
            val dir = if (test[index % test.length] == '<') -1 else 1
            if (can(block, x + dir, y)) x += dir;
            index++;
            if (!can(block, x, y - 1)) break;
            y -= 1;
        }
        for(i in 0 until 4) {
            if (block.size <= i) break;
            for(j in 0 until block[i].length) {
                if (block[i][j] == '#') cave[y + i][x + j] = true;
            }
        }
        for(i in maxH until maxH + 4) {
            for(j in 0 until 7) {
                if (cave[i][j]) maxH = Math.max(maxH, i + 1);
            }
        }
//        draw();
        val TEST = 40;
        if (maxH > TEST && left > 1000000000) {
            var caveState = ""
            var temp = 0;
            for(i in 0..TEST) {
                for(j in 0 until 7) {
                    caveState += if (cave[maxH - i - 1][j]) "1" else "0";
                    temp++;
                }
            }
            val state = State(caveState, blockIndex % 5, index % test.length);
            surfaces.add(caveState);
            if (stateToIndex.contains(state)) {
                // x + cycle * y >= Q
                // (Q - x) / cycle
                val cycleStart = stateToIndex.getValue(state);
                val cycleLen = blockIndex - cycleStart;
                val cycles = (END - cycleStart) / cycleLen - 1;
                return cycleLen;
                println("Cycle: " + cycleStart.toString() + " " + cycleLen.toString());
                left -= cycles * cycleLen;
                add = cycles * (maxH - stateToHeight.getValue(state));
            } else {
                stateToIndex[state] = blockIndex;
                stateToHeight[state] = maxH;
            }
        }
    }
    return 0;
}

fun main() {
    var it = 0;
    while(true) {
        it++;
        if (it % 100 == 0) println("It: " + it.toString());
        val test = (1..10000).map { arrayOf("<", ">") [Random.nextInt(0, 2)] }.joinToString("")
//        println(test)
        if (cycleLen(test) > 3000) {
            println(test);
            println(cycleLen(test));
        }
    }
}
