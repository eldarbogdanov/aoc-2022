fun main() {
    val input = ""
    var score = 0;
    for(s in input.split("\n")) {
        val (a, res) = s.split(" ");
        val b: String;
        if (res == "X") b = "" + ('X' + (a[0] - 'A' + 2) % 3).toChar(); else
        if (res == "Y") b = "" + ('X' + (a[0] - 'A')).toChar(); else
        if (res == "Z") b = "" + ('X' + (a[0] - 'A' + 1) % 3).toChar(); else b = "";
        if (b == "X") score += 1;
        if (b == "Y") score += 2;
        if (b == "Z") score += 3;
        if (a[0] - 'A' == b[0] - 'X') score += 3;
        if (a == "A" && b == "Y" || a == "B" && b == "Z" || a == "C" && b == "X") score += 6;
    }
	println(score);
}

