
fun main() {
    val input = ""
    var cnt = 0;
    for(s in input.split("\n")) {
		val (s1, s2) = s.split(",");
        val (L1, R1) = s1.split("-");
        val (L2, R2) = s2.split("-");
        val l1 = Integer.parseInt(L1);
        val l2 = Integer.parseInt(L2);
        val r1 = Integer.parseInt(R1);
        val r2 = Integer.parseInt(R2);
        if (!(r1 < l2 || r2 < l1)) ++cnt;
    }
	println(cnt);
}

