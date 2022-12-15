import kotlin.math.abs

fun main() {
    val test = """Sensor at x=220580, y=684270: closest beacon is at x=436611, y=263737
Sensor at x=3329538, y=3016377: closest beacon is at x=3355914, y=2862466
Sensor at x=2605308, y=2023938: closest beacon is at x=2197530, y=2271330
Sensor at x=1810202, y=3423309: closest beacon is at x=1829362, y=3182862
Sensor at x=480296, y=3999646: closest beacon is at x=1694700, y=4178942
Sensor at x=46556, y=1283362: closest beacon is at x=-91140, y=1441882
Sensor at x=3741660, y=3959257: closest beacon is at x=3537901, y=3368697
Sensor at x=3399994, y=700264: closest beacon is at x=3748004, y=2000000
Sensor at x=1531981, y=3801761: closest beacon is at x=1694700, y=4178942
Sensor at x=193367, y=2712458: closest beacon is at x=-91140, y=1441882
Sensor at x=3199067, y=2194575: closest beacon is at x=3748004, y=2000000
Sensor at x=1878117, y=2578817: closest beacon is at x=2197530, y=2271330
Sensor at x=2439089, y=3168242: closest beacon is at x=1829362, y=3182862
Sensor at x=273443, y=171076: closest beacon is at x=436611, y=263737
Sensor at x=3680413, y=2477027: closest beacon is at x=3748004, y=2000000
Sensor at x=3620241, y=2904998: closest beacon is at x=3355914, y=2862466
Sensor at x=1728351, y=2895399: closest beacon is at x=1829362, y=3182862
Sensor at x=1894207, y=1168355: closest beacon is at x=2197530, y=2271330
Sensor at x=856867, y=3271314: closest beacon is at x=1829362, y=3182862
Sensor at x=3056788, y=2626224: closest beacon is at x=3355914, y=2862466
Sensor at x=3598024, y=3322247: closest beacon is at x=3537901, y=3368697
Sensor at x=1662543, y=3128823: closest beacon is at x=1829362, y=3182862
Sensor at x=3992558, y=1933059: closest beacon is at x=3748004, y=2000000
Sensor at x=1844282, y=2994285: closest beacon is at x=1829362, y=3182862
Sensor at x=3604375, y=3668021: closest beacon is at x=3537901, y=3368697
Sensor at x=2569893, y=3911832: closest beacon is at x=1694700, y=4178942
Sensor at x=117970, y=37503: closest beacon is at x=436611, y=263737
Sensor at x=3951385, y=3125577: closest beacon is at x=3537901, y=3368697
Sensor at x=2482373, y=2648092: closest beacon is at x=2197530, y=2271330
Sensor at x=915040, y=1835970: closest beacon is at x=-91140, y=1441882
Sensor at x=3047883, y=3301452: closest beacon is at x=3537901, y=3368697
Sensor at x=117432, y=1503889: closest beacon is at x=-91140, y=1441882
Sensor at x=1136011, y=261705: closest beacon is at x=436611, y=263737
Sensor at x=2343111, y=66183: closest beacon is at x=2081841, y=-807749
Sensor at x=608229, y=955721: closest beacon is at x=436611, y=263737
Sensor at x=1189379, y=3999750: closest beacon is at x=1694700, y=4178942
Sensor at x=766640, y=26597: closest beacon is at x=436611, y=263737
Sensor at x=3891093, y=2110588: closest beacon is at x=3748004, y=2000000"""

    fun manhattan(a: Pair<Int, Int>, b: Pair<Int, Int>): Int {
        return abs(a.first - b.first) + abs(a.second - b.second);
    }

    val regex = Regex("Sensor at x=(-?[0-9]+), y=(-?[0-9]+): closest beacon is at x=(-?[0-9]+), y=(-?[0-9]+)")
    val data: MutableList<Pair<Pair<Int, Int>, Pair<Int, Int>>> = mutableListOf();
    for(s in test.split("\n")) {
        val groups = regex.find(s)!!.groupValues;
        val sx = Integer.parseInt(groups[1]);
        val sy = Integer.parseInt(groups[2]);
        val bx = Integer.parseInt(groups[3]);
        val by = Integer.parseInt(groups[4]);
        data.add(Pair(Pair(sx, sy), Pair(bx, by)));
    }

    val MIN = 0;
    val MAX = 4000000;
    for(y in MIN..MAX) {
        val intervals = mutableListOf<Pair<Int, Int>>();
        for(pair in data) {
            val d = manhattan(pair.first, pair.second);
            if (abs(pair.first.second - y) > d) continue;
            val free = d - abs(pair.first.second - y);
            intervals.add(Pair(pair.first.first - free, pair.first.first + free));
        }
        val candidates = mutableListOf<Int>();
        for(interval in intervals) {
            if (interval.first - 1 >= MIN) candidates.add(interval.first - 1);
            if (interval.second + 1 <= MAX) candidates.add(interval.second + 1);
        }
        for(x in candidates) {
            var can = true;
            for(interval in intervals) {
                if (x in interval.first..interval.second) {
                    can = false;
                    break;
                };
            }
            if (can) {
                println(x.toString() + " " + y.toString())
                println(x * 4000000L + y);
            };
        }
    }
}
