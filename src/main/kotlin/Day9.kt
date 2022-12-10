import java.io.File
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt
fun main(){
    println(main_part1())

    println(main_part2())
}

fun main_part2(): Int {
    val directionDict = mapOf("R" to Move(1,0), "L" to Move(-1,0), "U" to Move(0,1), "D" to Move(0,-1))
    val input = File("/Users/nbietry/IdeaProjects/AoC2022/inputs/input9").readLines().map { line -> line.split(" ")
        .let{ (direction, iteration) -> directionDict[direction]!! to iteration.toInt()} }

    val knots = MutableList(10){Pos(0,0)}

    val visitedCoordinate : MutableSet<Pos> = mutableSetOf(knots[9])
    for((direction, iteration) in input){
        for(move in 0 until iteration){
            for((index) in knots.withIndex()) {
                if(index == 0) knots[index] = knots[index] + direction
                else {
                    knots[index] = knots[index] + moveTail(knots[index-1], knots[index])
                    if (index == 9) visitedCoordinate += knots[index]
                }
            }
        }
    }
    return(visitedCoordinate.size)
}

fun main_part1(): Int{
    val directionDict = mapOf("R" to Move(1,0), "L" to Move(-1,0), "U" to Move(0,1), "D" to Move(0,-1))
    val input = File("/Users/nbietry/IdeaProjects/AoC2022/inputs/input9").readLines().map { line -> line.split(" ")
        .let{ (direction, iteration) -> directionDict[direction]!! to iteration.toInt()} }

    var head = Pos(0,0)
    var tail = Pos(0,0)
    val visitedCoordinate : MutableSet<Pos> = mutableSetOf(tail)

    for((direction, iteration) in input){
        for(move in 0 until iteration){
            head += direction
            tail += moveTail(head, tail)
            visitedCoordinate += tail
        }
    }
    return(visitedCoordinate.size)
}

data class Pos(val x:Int, val y:Int){
    override fun toString(): String {
        return "(x:$x,y:$y)"
    }
}
operator fun Pos.plus(move: Move): Pos = copy(x + move.dx, y + move.dy)
operator fun Pos.minus(point2: Pos): Move = Move(x-point2.x, y-point2.y)
data class Move(val dx:Int, val dy:Int)

fun moveTail(head: Pos, tail: Pos): Move{
    val dist = distance(head, tail)
    //println("H:$head T:$tail D:$dist")
    return if(dist < 2)
        Move(0,0)
    else
        Move((head-tail).dx.coerceIn(-1,1),(head-tail).dy.coerceIn(-1,1))
}
fun distance(point1: Pos, point2 : Pos): Int {
    val distance = sqrt((point2.x - point1.x).toDouble().pow(2.0) + (point2.y - point1.y).toDouble().pow(2.0) )
    return distance.roundToInt()
}