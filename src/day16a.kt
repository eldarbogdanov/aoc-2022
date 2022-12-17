fun main() {
    val test = """Valve OS has flow rate=0; tunnels lead to valves EE, CL
Valve EN has flow rate=0; tunnels lead to valves CL, GV
Valve RR has flow rate=24; tunnels lead to valves FS, YP
Valve VB has flow rate=20; tunnels lead to valves UU, EY, SG, ZB
Valve UU has flow rate=0; tunnels lead to valves OT, VB
Valve WH has flow rate=0; tunnels lead to valves CS, JS
Valve OF has flow rate=25; tunnel leads to valve YM
Valve TY has flow rate=0; tunnels lead to valves AA, GQ
Valve RV has flow rate=0; tunnels lead to valves BT, YX
Valve GK has flow rate=0; tunnels lead to valves GD, AA
Valve EL has flow rate=0; tunnels lead to valves EK, EE
Valve OT has flow rate=9; tunnels lead to valves YR, BJ, OX, UU, HJ
Valve DG has flow rate=11; tunnels lead to valves BN, QE
Valve YR has flow rate=0; tunnels lead to valves OT, YX
Valve GV has flow rate=0; tunnels lead to valves AA, EN
Valve BN has flow rate=0; tunnels lead to valves DG, LU
Valve FS has flow rate=0; tunnels lead to valves TI, RR
Valve DW has flow rate=0; tunnels lead to valves SS, MS
Valve DJ has flow rate=0; tunnels lead to valves KY, GD
Valve BJ has flow rate=0; tunnels lead to valves OT, BT
Valve KY has flow rate=0; tunnels lead to valves EE, DJ
Valve YP has flow rate=0; tunnels lead to valves YM, RR
Valve LU has flow rate=0; tunnels lead to valves BN, CS
Valve OX has flow rate=0; tunnels lead to valves OT, XD
Valve ZB has flow rate=0; tunnels lead to valves VB, PP
Valve CL has flow rate=10; tunnels lead to valves KQ, EN, OS, MQ
Valve XD has flow rate=0; tunnels lead to valves KR, OX
Valve YM has flow rate=0; tunnels lead to valves OF, YP
Valve EY has flow rate=0; tunnels lead to valves MS, VB
Valve KQ has flow rate=0; tunnels lead to valves CS, CL
Valve SS has flow rate=0; tunnels lead to valves AA, DW
Valve SG has flow rate=0; tunnels lead to valves VB, KR
Valve EE has flow rate=22; tunnels lead to valves XR, OS, KY, EL
Valve OI has flow rate=0; tunnels lead to valves RE, MS
Valve QE has flow rate=0; tunnels lead to valves DG, GD
Valve GD has flow rate=3; tunnels lead to valves GK, DJ, MQ, QE, JS
Valve EK has flow rate=23; tunnel leads to valve EL
Valve GQ has flow rate=0; tunnels lead to valves CS, TY
Valve CS has flow rate=7; tunnels lead to valves GQ, WH, KQ, LU
Valve MS has flow rate=4; tunnels lead to valves HJ, EY, DW, OI
Valve XR has flow rate=0; tunnels lead to valves EE, AA
Valve RE has flow rate=6; tunnels lead to valves TI, PP, OI
Valve KR has flow rate=17; tunnels lead to valves XD, SG
Valve BT has flow rate=15; tunnels lead to valves BJ, RV
Valve PP has flow rate=0; tunnels lead to valves RE, ZB
Valve TI has flow rate=0; tunnels lead to valves RE, FS
Valve HJ has flow rate=0; tunnels lead to valves OT, MS
Valve AA has flow rate=0; tunnels lead to valves GK, GV, SS, XR, TY
Valve MQ has flow rate=0; tunnels lead to valves GD, CL
Valve JS has flow rate=0; tunnels lead to valves GD, WH
Valve YX has flow rate=5; tunnels lead to valves YR, RV
    """.trimIndent()

    val MAX = 60;
    val regex = Regex("Valve ([A-Z][A-Z]) has flow rate=([0-9]+); tunnels? leads? to valves? (.*)")
    val nameToIndex: MutableMap<String, Int> = mutableMapOf()
    val capacity = Array(MAX) { 0 }
    val dist = Array(MAX) { Array(MAX) { 111110 } }

    fun nameIndex(name: String): Int {
        if (nameToIndex.contains(name)) return nameToIndex.getValue(name);
        nameToIndex[name] = nameToIndex.size;
        return nameToIndex.getValue(name);
    }

    for(s in test.split("\n")) {
        val groups = regex.find(s)!!.groupValues
        val valve = nameIndex(groups[1])
        capacity[valve] = Integer.parseInt(groups[2])
        for(connect in groups[3].split(", ")) {
            val valve2 = nameIndex(connect);
            dist[valve][valve2] = 1;
        }
    }

    for(k in 0 until dist.size) {
        for(i in 0 until dist.size) {
            for(j in 0 until dist.size) {
                if (dist[i][k] + dist[k][j] < dist[i][j]) {
                    dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
    }

    data class State(val cur: Int, val used: List<Boolean>, val timeLeft: Int)
    val mem: MutableMap<State, Int> = mutableMapOf();
    fun rec(cur: Int, used: Array<Boolean>, timeLeft: Int, flow: Int, sum: Int): Int {
        if (mem.contains(State(cur, used.toList(), timeLeft))) {
            return mem.getValue(State(cur, used.toList(), timeLeft));
        }
//        println(String.format("%s %s %s %s %s", cur, used.toList(), timeLeft, flow, sum));
        if (timeLeft < 0) return -1000000000;
        var ret = sum + timeLeft * flow;
        for(next in 0 until dist.size) {
            if (capacity[next] == 0) continue;
            if (used[next]) continue;
            used[next] = true;
            ret = Math.max(ret, rec(
                next,
                used,
                timeLeft - dist[cur][next] - 1,
                flow + capacity[next],
                sum + (dist[cur][next] + 1) * flow
            ));
            used[next] = false;
        }
        return ret;
    }

    println(nameToIndex);
    println(rec(nameIndex("AA"), Array(MAX) {false}, 30, 0, 0));
}

/*
Valve OS has flow rate=0; tunnels lead to valves EE, CL
Valve EN has flow rate=0; tunnels lead to valves CL, GV
Valve RR has flow rate=24; tunnels lead to valves FS, YP
Valve VB has flow rate=20; tunnels lead to valves UU, EY, SG, ZB
Valve UU has flow rate=0; tunnels lead to valves OT, VB
Valve WH has flow rate=0; tunnels lead to valves CS, JS
Valve OF has flow rate=25; tunnel leads to valve YM
Valve TY has flow rate=0; tunnels lead to valves AA, GQ
Valve RV has flow rate=0; tunnels lead to valves BT, YX
Valve GK has flow rate=0; tunnels lead to valves GD, AA
Valve EL has flow rate=0; tunnels lead to valves EK, EE
Valve OT has flow rate=9; tunnels lead to valves YR, BJ, OX, UU, HJ
Valve DG has flow rate=11; tunnels lead to valves BN, QE
Valve YR has flow rate=0; tunnels lead to valves OT, YX
Valve GV has flow rate=0; tunnels lead to valves AA, EN
Valve BN has flow rate=0; tunnels lead to valves DG, LU
Valve FS has flow rate=0; tunnels lead to valves TI, RR
Valve DW has flow rate=0; tunnels lead to valves SS, MS
Valve DJ has flow rate=0; tunnels lead to valves KY, GD
Valve BJ has flow rate=0; tunnels lead to valves OT, BT
Valve KY has flow rate=0; tunnels lead to valves EE, DJ
Valve YP has flow rate=0; tunnels lead to valves YM, RR
Valve LU has flow rate=0; tunnels lead to valves BN, CS
Valve OX has flow rate=0; tunnels lead to valves OT, XD
Valve ZB has flow rate=0; tunnels lead to valves VB, PP
Valve CL has flow rate=10; tunnels lead to valves KQ, EN, OS, MQ
Valve XD has flow rate=0; tunnels lead to valves KR, OX
Valve YM has flow rate=0; tunnels lead to valves OF, YP
Valve EY has flow rate=0; tunnels lead to valves MS, VB
Valve KQ has flow rate=0; tunnels lead to valves CS, CL
Valve SS has flow rate=0; tunnels lead to valves AA, DW
Valve SG has flow rate=0; tunnels lead to valves VB, KR
Valve EE has flow rate=22; tunnels lead to valves XR, OS, KY, EL
Valve OI has flow rate=0; tunnels lead to valves RE, MS
Valve QE has flow rate=0; tunnels lead to valves DG, GD
Valve GD has flow rate=3; tunnels lead to valves GK, DJ, MQ, QE, JS
Valve EK has flow rate=23; tunnel leads to valve EL
Valve GQ has flow rate=0; tunnels lead to valves CS, TY
Valve CS has flow rate=7; tunnels lead to valves GQ, WH, KQ, LU
Valve MS has flow rate=4; tunnels lead to valves HJ, EY, DW, OI
Valve XR has flow rate=0; tunnels lead to valves EE, AA
Valve RE has flow rate=6; tunnels lead to valves TI, PP, OI
Valve KR has flow rate=17; tunnels lead to valves XD, SG
Valve BT has flow rate=15; tunnels lead to valves BJ, RV
Valve PP has flow rate=0; tunnels lead to valves RE, ZB
Valve TI has flow rate=0; tunnels lead to valves RE, FS
Valve HJ has flow rate=0; tunnels lead to valves OT, MS
Valve AA has flow rate=0; tunnels lead to valves GK, GV, SS, XR, TY
Valve MQ has flow rate=0; tunnels lead to valves GD, CL
Valve JS has flow rate=0; tunnels lead to valves GD, WH
Valve YX has flow rate=5; tunnels lead to valves YR, RV
        */
