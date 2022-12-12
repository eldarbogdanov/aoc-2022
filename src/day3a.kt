fun main() {
    val input = ""
    var score = 0;
    for(s in input.split("\n")) {
        val len = s.length;
        val st = s.substring(0, len / 2).toCharArray().intersect(
            Iterable { s.substring(len / 2, len).asSequence().iterator() }
        );
        val ch = st.first();
		score += if (ch in 'a'..'z') ch - 'a' + 1 else ch - 'A' + 27;
    }
	println(score);
}

