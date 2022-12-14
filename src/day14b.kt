fun main() {
    val test = """498,4 -> 498,6 -> 496,6
503,4 -> 502,4 -> 502,9 -> 494,9"""

    val st: MutableSet<Pair<Int, Int>> = mutableSetOf();
    var maxY = 0;
    for(s in test.split("\n")) {
        val t = s.split(" -> ");
        val points = t.map{ Pair(
            Integer.parseInt(it.split(",")[0]),
            Integer.parseInt(it.split(",")[1])
        ) };
        var last: Pair<Int, Int>? = null;
        for(point in points) {
            maxY = Math.max(maxY, point.second);
            if (last == null) {
                last = point;
                continue;
            }
            if (last.first == point.first) {
                for(i in Math.min(last.second, point.second)..Math.max(last.second, point.second)) {
                    st.add(Pair(last.first, i));
                }
            } else {
                for(i in Math.min(last.first, point.first)..Math.max(last.first, point.first)) {
                    st.add(Pair(i, last.second));
                }
            }
            last = point;
        }
    }
    for(x in -500..1000) st.add(Pair(x, maxY + 2));
    var ans = 0;
    while(!st.contains(Pair(500, 0))) {
        var x = 500;
        var y = 0;
        while(true) {
            if (!st.contains(Pair(x, y + 1))) {
                ++y;
            } else if (!st.contains(Pair(x - 1, y + 1))) {
                --x;
                ++y;
            } else if (!st.contains(Pair(x + 1, y + 1))) {
                ++x;
                ++y;
            } else break;
        }
        st.add(Pair(x, y));
        ++ans;
    }
    println(ans);
}
