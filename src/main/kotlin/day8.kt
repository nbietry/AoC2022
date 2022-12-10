import java.io.File

fun main(){
    println("Result part1 : " + mainPart1())
    println("Resukt part2 : " + mainPart2())
}
fun mainPart1(): Int {
    val input = File("/Users/nbietry/IdeaProjects/AoC2022/inputs/input8").readLines()

    val stackHorizontal : Array<ArrayList<Int>> = Array(input[0].length) { ArrayList() }
    val stackVertical : Array<ArrayList<Int>> = Array(input.size) { ArrayList() }


    for((indexLin, line) in input.withIndex()){
        for((indexCol, value) in line.toCharArray().map { x -> x.toString().toInt() }.withIndex()) {
            stackHorizontal[indexLin].add(value)
            stackVertical[indexCol].add(value)
        }
    }

    var globalCount = 0
    //println(stackHorizontal.map{x -> x.toString()})
    //println(stackVertical.map{x -> x.toString()})

    for((index, line) in stackHorizontal.withIndex()){
        for((indexCol, col) in line.toList().map { x -> x.toString().toInt() }.withIndex()) {
            var test1 = isVisibile(indexCol, line)
            var test2 = isVisibile(line.size-1 - indexCol, line.reversed())
            var test3 = isVisibile(index,stackVertical[indexCol])
            var test4 = isVisibile(stackVertical[indexCol].size-1 - index,stackVertical[indexCol].reversed())
            if(test1 || test2 || test3 || test4) globalCount++
        }
    }
    return globalCount
}
fun isVisibile(position: Int, trees: List<Int>): Boolean{

    var returnValue = true
    for(treeIndex in 0 until position){
        if(trees[treeIndex] >= trees[position]) returnValue = false
    }
    //println(position.toString() + " " + trees.elementAt(position).toString() + " " + trees + " " + returnValue)
    return returnValue
}
fun mainPart2(): Int{
    val input = File("/Users/nbietry/IdeaProjects/AoC2022/inputs/input8").readLines()

    val stackHorizontal : Array<ArrayList<Int>> = Array(input[0].length) { ArrayList() }
    val stackVertical : Array<ArrayList<Int>> = Array(input.size) { ArrayList() }

    val stackResult : Array<ArrayList<Int>> = Array(input[0].length) { ArrayList() }

    for((indexLin, line) in input.withIndex()){
        for((indexCol, value) in line.toCharArray().map { x -> x.toString().toInt() }.withIndex()) {
            stackHorizontal[indexLin].add(value)
            stackVertical[indexCol].add(value)
        }
    }
    var globalCount = 0
    for((index, line) in stackHorizontal.withIndex()){
        for((indexCol, col) in line.toList().map { x -> x.toString().toInt() }.withIndex()) {
            var test1 = computeVisibility(indexCol, line)
            var test2 = computeVisibility(index,stackVertical[indexCol])
            stackResult[index].add(test1 * test2)
        }
    }
    //println(stackResult.map { x->x.toString() })
    return stackResult.map { x-> x.max()}.max();
}
fun computeVisibility(position: Int, trees: List<Int>): Int{

    var valueUp = 0
    var valueDown = 0
    var treeIndex = position
    do{
        if(treeIndex > 0) {
            valueUp++
            treeIndex--
        }
    }while (trees[treeIndex] < trees[position] && treeIndex > 0)
    //println("Left = $trees Value:$valueUp Position:$position")

    treeIndex = position
    do{
        if(treeIndex < trees.size-1) {
            valueDown++
            treeIndex++
        }
    }while (treeIndex < trees.size-1 && trees[treeIndex] < trees[position])
    //println("Right = $trees Value:$valueDown Position:$position")

    //println(valueUp * valueDown)
    return valueUp * valueDown
}