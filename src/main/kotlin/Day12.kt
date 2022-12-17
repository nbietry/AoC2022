import nico.Graph
import java.io.File
import kotlin.math.abs

fun main(){
    val input = File("/Users/nbietry/IdeaProjects/AoC2022/inputs/input12").readText().split("\n").map { cells ->
        cells.toCharArray()}

    //println(abs('a'-'c'))
    val field = Graph()
    for(y in input.indices)
        for(x in input[y].indices)
            createVertex(x, y, field, input)
    println(field.dfs("0-0", "20-132"))
}

fun distance(source: Char, target: Char): Int{
    val sourceAdj = when (source) {'S'-> 'a' else -> source}
    val targetAdj = when (target) {'E'-> 'z' else -> target}
    return abs(sourceAdj - targetAdj)
}

fun createVertex(x: Int, y: Int, field: Graph, input: List<CharArray>) {
    field.addVertex("$y-$x")
    if(input.getOrNull(y+1)?.getOrNull(x) != null && distance(input[y][x], input[y+1][x]) < 2) {
        field.addVertex((y+1).toString()+'-'+x.toString())
        field.connect("$y-$x", (y+1).toString()+'-'+x.toString())
    }
    if(input.getOrNull(y)?.getOrNull(x+1) != null && distance(input[y][x],input[y][x+1]) < 2) {
        field.addVertex(y.toString()+'-'+(x+1).toString())
        field.connect("$y-$x", y.toString()+'-'+(x+1).toString())
    }
    if(input.getOrNull(y-1)?.getOrNull(x) != null && distance(input[y][x],input[y-1][x]) < 2) {
        field.addVertex((y-1).toString()+'-'+x.toString())
        field.connect("$y-$x", (y-1).toString()+'-'+x.toString())
    }
    if(input.getOrNull(y)?.getOrNull(x-1) != null && distance(input[y][x], input[y][x-1]) < 2) {
        field.addVertex(y.toString()+'-'+(x-1).toString())
        field.connect("$y-$x", y.toString()+'-'+(x-1).toString())
    }
}


