import kotlin.math.max

fun main() {
    data class State(
        val oreRobots: Int, val clayRobots: Int, val obsidianRobots: Int, val geodeRobots: Int,
        val clay: Int, val obsidian: Int, val geode: Int
    ) {
        fun next(): State {
            return State(
                oreRobots, clayRobots, obsidianRobots, geodeRobots,
                clay + clayRobots, obsidian + obsidianRobots, geode + geodeRobots
            )
        }
    }
    fun solve(time: Int, oreOreCost: Int, clayOreCost: Int, obsidianOreCost: Int, obsidianClayCost: Int, geodeOreCost: Int, geodeObsidianCost: Int): Int {
        val best = Array(time + 1) { mutableMapOf<State, Int>() }
        best[0][State(1, 0, 0, 0, 0, 0, 0)] = 0
        var ret = 0

        fun put(t: Int, state: State, value: Int) {
            if (!best[t + 1].contains(state) || best[t + 1].getValue(state) < value) {
                best[t + 1][state] = value
            }
        }

        for(t in 0 until time) {
            for(entry in best[t]) {
                val state = entry.key
                ret = max(ret, state.next().geode)
                if (t == time - 1) continue;

                // don't build anything
                put(t, state.next(), entry.value + state.oreRobots);
                // build ore robot
                if (entry.value >= oreOreCost) {
                    val newState = state.next().copy(oreRobots = state.oreRobots + 1)
                    val newOre = entry.value - oreOreCost + state.oreRobots
                    put(t, newState, newOre)
                }
                // build clay robot
                if (entry.value >= clayOreCost) {
                    val newState = state.next().copy(clayRobots = state.clayRobots + 1)
                    val newOre = entry.value - clayOreCost + state.oreRobots
                    put(t, newState, newOre)
                }
                // build obsidian robot
                if (entry.value >= obsidianOreCost && state.clay >= obsidianClayCost) {
                    val newState = state.next().copy(obsidianRobots = state.obsidianRobots + 1, clay = state.clay - obsidianClayCost + state.clayRobots)
                    val newOre = entry.value - obsidianOreCost + state.oreRobots
                    put(t, newState, newOre)
                }
                // build geode robot
                if (entry.value >= geodeOreCost && state.obsidian >= geodeObsidianCost) {
                    val newState = state.next().copy(geodeRobots = state.geodeRobots + 1, obsidian = state.obsidian - geodeObsidianCost + state.obsidianRobots)
                    val newOre = entry.value - geodeOreCost + state.oreRobots
                    put(t, newState, newOre)
                }
            }
        }
        return ret
    }

//    val test = """Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian.
//Blueprint 2: Each ore robot costs 2 ore. Each clay robot costs 3 ore. Each obsidian robot costs 3 ore and 8 clay. Each geode robot costs 3 ore and 12 obsidian."""
    val test = """Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 4 ore. Each obsidian robot costs 2 ore and 16 clay. Each geode robot costs 4 ore and 16 obsidian.
Blueprint 2: Each ore robot costs 4 ore. Each clay robot costs 4 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 4 ore and 8 obsidian.
Blueprint 3: Each ore robot costs 3 ore. Each clay robot costs 4 ore. Each obsidian robot costs 3 ore and 18 clay. Each geode robot costs 4 ore and 16 obsidian."""
    val regex = Regex("Blueprint ([0-9]+): Each ore robot costs ([0-9]+) ore. Each clay robot costs ([0-9]+) ore. Each obsidian robot costs ([0-9]+) ore and ([0-9]+) clay. Each geode robot costs ([0-9]+) ore and ([0-9]+) obsidian.")
    var ans = 0;
    for(s in test.split("\n")) {
        val list = regex.find(s)!!.groupValues
        println("Test " + list[1])
        val geodes = solve(
            32,
            Integer.parseInt(list[2]),
            Integer.parseInt(list[3]),
            Integer.parseInt(list[4]),
            Integer.parseInt(list[5]),
            Integer.parseInt(list[6]),
            Integer.parseInt(list[7]),
        )
        println(geodes)
        ans += Integer.parseInt(list[1]) * geodes
    }
    println(ans)
}
