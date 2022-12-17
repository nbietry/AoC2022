import java.io.File
import nico.extractNumbers

fun main() {
    val input = File("/Users/nbietry/IdeaProjects/AoC2022/inputs/input11").readText().split("\n\n").map { monkey ->
        monkey.split("\n") }

    val monkeyList = input.map { monkey -> val(items, ope, test, ifTrue, ifFalse) = monkey.drop(1)
        Monkey(
            extractNumbers(items),
            extractOperation(ope.substringAfter("new = ")),
            extractNumbers(test).single(),
            extractNumbers(ifTrue).single(),
            extractNumbers(ifFalse).single()
        )
    }

    run part1@{
        for (turn in 0 until 20) {
            for (monkey in monkeyList) {
                monkey.inspectItem(monkeyList)
            }
        }
        val list = monkeyList.sortedWith(compareBy { it.countInspection }).reversed()
        //println(list.toString())
        println(list[0].countInspection * list[1].countInspection)
    }

    run part2@{
        for (turn in 0 until 10000) {
            for (monkey in monkeyList) {
                monkey.inspectItem(monkeyList)
            }
        }
        val list = monkeyList.sortedWith(compareBy { it.countInspection }).reversed()
        println(monkeyList.toString())
        println(list[0].countInspection * list[1].countInspection)
    }


}

fun extractOperation(input: String): (Long) -> Long{
    val (_, operator, op2) = input.split(" ")

    return  when(operator){
        "+" -> {old -> old + (op2.toLongOrNull()?: old)}
        "*" -> {old -> old * (op2.toLongOrNull()?: old)}
        else -> error(input)
    }
}

class Monkey(
    val items: MutableList<Long>,
    val operation: (Long) -> Long,
    val test: Long,
    val ifTrue: Long,
    val ifFalse: Long,
    var countInspection: Int = 0
){
    fun inspectItem(monkeyList: List<Monkey>) {
        while(items.isNotEmpty()) {
            val worriness = operation(items.first().toLong()) ///3
            val test = ((worriness % test) == 0L)
            items.removeFirst()
            countInspection++
            if (!test) {
                //println("Worry: $worriness, Throw item to monkey: $ifFalse")
                monkeyList[ifFalse.toInt()].items.add(worriness)
            } else {
                //println("Worry: $worriness, Throw item to monkey: $ifTrue")
                monkeyList[ifTrue.toInt()].items.add(worriness)
            }
        }
    }
    override fun toString(): String {
        return "$items, $countInspection"
    }
}
