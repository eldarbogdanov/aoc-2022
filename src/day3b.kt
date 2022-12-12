fun main() {
    val input = ""
    var score = 0;
    var it = 0;
    var st: Set<Char> = HashSet<Char>();
    for(s in input.split("\n")) {
        if (it % 3 == 0) st = s.toSet();
        it += 1;
        st = s.toCharArray().intersect(Iterable { st.iterator() });
        
        if (it % 3 == 0) {
	        val ch = st.first();
			score += if (ch in 'a'..'z') ch - 'a' + 1 else ch - 'A' + 27;
        }
    }
	println(score);
}

