fun main() {
    val test = ""
    for((i, _) in test.withIndex()) {
        //println(test.substring(i, i + 4).toSet());
        if (test.substring(i, i + 14).toSet().size == 14) {
            println(i + 14);
            return ;
        }
    }
}

