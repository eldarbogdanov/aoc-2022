/*
Monkey 0:
  Starting items: 83, 88, 96, 79, 86, 88, 70
  Operation: new = old * 5
  Test: divisible by 11
    If true: throw to monkey 2
    If false: throw to monkey 3

Monkey 1:
  Starting items: 59, 63, 98, 85, 68, 72
  Operation: new = old * 11
  Test: divisible by 5
    If true: throw to monkey 4
    If false: throw to monkey 0

Monkey 2:
  Starting items: 90, 79, 97, 52, 90, 94, 71, 70
  Operation: new = old + 2
  Test: divisible by 19
    If true: throw to monkey 5
    If false: throw to monkey 6

Monkey 3:
  Starting items: 97, 55, 62
  Operation: new = old + 5
  Test: divisible by 13
    If true: throw to monkey 2
    If false: throw to monkey 6

Monkey 4:
  Starting items: 74, 54, 94, 76
  Operation: new = old * old
  Test: divisible by 7
    If true: throw to monkey 0
    If false: throw to monkey 3

Monkey 5:
  Starting items: 58
  Operation: new = old + 4
  Test: divisible by 17
    If true: throw to monkey 7
    If false: throw to monkey 1

Monkey 6:
  Starting items: 66, 63
  Operation: new = old + 6
  Test: divisible by 2
    If true: throw to monkey 7
    If false: throw to monkey 5

Monkey 7:
  Starting items: 56, 56, 90, 96, 68
  Operation: new = old + 7
  Test: divisible by 3
    If true: throw to monkey 4
    If false: throw to monkey 1
 */

class Monkey(
    val items: MutableList<Long>,
    val op: (Long) -> Long,
    val test: (Long) -> Boolean,
    val successDir: Int,
    val failDir: Int
);

fun toTest(divisor: Int): (Long) -> Boolean {
    return { x: Long -> x % divisor == 0L };
}

val MODULO = 223092870; // product of monkey divisors (test and real data)

fun main() {
    val monkeys: Array<Monkey> = arrayOf(
        Monkey(
            mutableListOf(83, 88, 96, 79, 86, 88, 70),
            { old: Long -> old * 5 },
            toTest(11),
            2,
            3
        ),
        Monkey(
            mutableListOf(59, 63, 98, 85, 68, 72),
            { old: Long -> old * 11 },
            toTest(5),
            4,
            0
        ),
        Monkey(
            mutableListOf(90, 79, 97, 52, 90, 94, 71, 70),
            { old: Long -> old + 2 },
            toTest(19),
            5,
            6
        ),
        Monkey(
            mutableListOf(97, 55, 62),
            { old: Long -> old + 5 },
            toTest(13),
            2,
            6
        ),
        Monkey(
            mutableListOf(74, 54, 94, 76),
            { old: Long -> old * old },
            toTest(7),
            0,
            3
        ),
        Monkey(
            mutableListOf(58),
            { old: Long -> old + 4 },
            toTest(17),
            7,
            1
        ),
        Monkey(
            mutableListOf(66, 63),
            { old: Long -> old + 6 },
            toTest(2),
            7,
            5
        ),
        Monkey(
            mutableListOf(56, 56, 90, 96, 68),
            { old: Long -> old + 7 },
            toTest(3),
            4,
            1
        )
//        Monkey(
//            mutableListOf(79, 98),
//            { old: Long -> old * 19 },
//            toTest(23),
//            2,
//            3
//        ),
//        Monkey(
//            mutableListOf(54, 65, 75, 74),
//            { old: Long -> old + 6 },
//            toTest(19),
//            2,
//            0
//        ),
//        Monkey(
//            mutableListOf(79, 60, 97),
//            { old: Long -> old * old },
//            toTest(13),
//            1,
//            3
//        ),
//        Monkey(
//            mutableListOf(74),
//            { old: Long -> old + 3 },
//            toTest(17),
//            0,
//            1
//        )
    );

    val inspected = Array(8) { 0 };

    for(round in 1..10000) {
        //println(round);
        for((index, monkey) in monkeys.withIndex()) {
            //println(monkey.items);
            for(item in monkey.items) {
                val newWorry = monkey.op(item);
                val newMonkey = if (monkey.test(newWorry)) monkey.successDir else monkey.failDir;
                monkeys[newMonkey].items.add(newWorry % MODULO);
            }
            inspected[index] += monkey.items.size;
            monkey.items.clear();
        }
    }

    //println(inspected.toList());
    inspected.sortDescending();
    println(1L * inspected[0] * inspected[1]);
}
