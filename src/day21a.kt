fun main() {
    val test = """root: pppw + sjmn
dbpl: 5
cczh: sllz + lgvd
zczc: 2
ptdq: humn - dvpt
dvpt: 3
lfqf: 4
humn: 5
ljgn: 2
sjmn: drzm * dbpl
sllz: 4
pppw: cczh / lfqf
lgvd: ljgn * ptdq
drzm: hmdt - zczc
hmdt: 32"""
    open class Node(val name: String)
    class Leaf(name: String, val value: Long) : Node(name)
    class Parent(name: String, val left: String, val right: String, val op: (Long, Long) -> Long) : Node(name)
    val regex1 = Regex("([a-z][a-z][a-z][a-z]): ([a-z][a-z][a-z][a-z]) ([+-/*]) ([a-z][a-z][a-z][a-z])")
    val regex2 = Regex("([a-z][a-z][a-z][a-z]): ([0-9]+)")
    val map: MutableMap<String, Node> = mutableMapOf();
    fun operator(op: String): (Long, Long) -> Long {
        when(op) {
            "+" -> return { a: Long, b: Long -> a + b }
            "-" -> return { a: Long, b: Long -> a - b }
            "*" -> return { a: Long, b: Long -> a * b }
            "/" -> return { a: Long, b: Long -> a / b }
        }
        throw Exception("Invalid op " + op)
    }

    for(s in test.split("\n")) {
        if (regex1.find(s) != null) {
            val groups = regex1.find(s)!!.groupValues
            map[groups[1]] = Parent(groups[1], groups[2], groups[4], operator(groups[3]))
        } else {
            val groups = regex2.find(s)!!.groupValues
            map[groups[1]] = Leaf(groups[1], Integer.parseInt(groups[2]).toLong())
        }
    }
    val mem: MutableMap<String, Long> = mutableMapOf();
    fun rec(cur: String): Long {
        if (mem.contains(cur)) return mem.getValue(cur);
        val node = map[cur]!!;
        when(node) {
            is Leaf -> {
                mem[cur] = node.value
            }
            is Parent -> {
                mem[cur] = node.op(rec(node.left), rec(node.right))
            }
        }
        return mem[cur]!!
    }
    println(rec("root"))
}
