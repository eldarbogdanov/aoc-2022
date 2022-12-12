fun main() {
    val input = """"""
    var maxs = mutableListOf<Int>();
    for(s in input.split("\n\n")) {
        var sum = 0
        for (t in s.split("\n")) {
            if (t.equals("")) continue;
            sum += Integer.parseInt(t);
        }
        maxs.add(sum);
    }
	maxs.sort();
    val len = maxs.size;
    System.out.println(maxs[len - 1] + maxs[len - 2] + maxs[len - 3]);
}
