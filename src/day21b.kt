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
    class Parent(name: String, val left: String, val right: String, val opString: String, val op: (Long, Long) -> Long) : Node(name)
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
            map[groups[1]] = Parent(groups[1], groups[2], groups[4], groups[3], operator(groups[3]))
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

    fun rec3(cur: String): Boolean {
        val node = map[cur]!!
        when(node) {
            is Leaf -> return node.name == "humn"
            is Parent -> return rec3(node.left) or rec3(node.right)
        }
        throw Exception("Shouldnt get here")
    }

    fun reverse(res: Long, operator: String, operandValue: Long, isLeft: Boolean): Long {
        if (operator == "+") return res - operandValue
        if (operator == "*") return res / operandValue
        if (operator == "-") {
            if (isLeft) return res + operandValue
            else return operandValue - res
        }

        if (isLeft) return res * operandValue
        else return operandValue / res
    }

    fun rec2(cur: String, expected: Long): Long {
        val node = map[cur]!!
        when(node) {
            is Leaf -> {
                if (node.name == "humn") println(expected)
                return node.value
            }
            is Parent -> {
                val left = rec3(node.left)
                val right = rec3(node.right)
                if (left) {
                    return rec2(node.left, reverse(expected, node.opString, rec(node.right), true))
                }
                if (right) {
                    return rec2(node.right, reverse(expected, node.opString, rec(node.left), false))
                }
                return rec(cur)
            }
        }
        throw Exception("Shouldnt get here")
    }

    if (rec3((map["root"] as Parent).left)) {
        val x = rec((map["root"] as Parent).right)
        rec2((map["root"] as Parent).left, x)
    } else {
        val x = rec((map["root"] as Parent).left)
        rec2((map["root"] as Parent).right, x)
    }
}
