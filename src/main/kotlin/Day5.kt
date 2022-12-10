import java.io.File
import java.util.*

fun main() {
    val input = File("/Users/nbietry/IdeaProjects/AoC2022/inputs/input5").readText().split("\n\n")
    val part1 = input[0].lines().reversed().map {  x -> x.chunked(4)}
    val part2 = input[1].lines()

    val stack : List<ArrayDeque<String>> = List(part1.size) {ArrayDeque<String>()}
    for((indexLin, line) in part1.withIndex()){
        for((indexCol, column) in line.withIndex()){
            if(!column.isNullOrBlank()) stack[indexCol].push(column)
        }
    }

    for(move in part2){
        val match = Regex("(\\w+) (\\d+) (\\w+) (\\d+) (\\w+) (\\d+)").find(move)!!
        val (move, qty, from, src, to, dst) = match.destructured
        val tmpStack: ArrayDeque<String> = ArrayDeque<String>()
        for(i in 1..qty.toInt()) {
            tmpStack.addFirst(stack[src.toInt() - 1].pop())
        }
        for(i in 1..qty.toInt()) {
            stack[dst.toInt()-1].addFirst(tmpStack.pop())
        }
    }

    var response: String = ""
    for(column in stack) {
        response += column.pop()
    }
    println(response.replace("[", "").replace("]", "").replace(" ", ""))
}
