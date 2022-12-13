import java.io.File

fun main(){
    val input = File("/Users/nbietry/IdeaProjects/AoC2022/inputs/input10").readLines().map { line -> line.split(" ")}

    run part1@ {
        var x = 1
        var result = 0
        var cycle = 1
        input.forEach { instruction ->
            cycle++
            if (cycle.isSignalStrengt()) result += cycle * x
            //println("Cycle " + cycle + " " + input[cursorInput] + " pending " + pendingCycle)
            if (instruction.size > 1) {
                cycle++
                x += instruction[1].toInt()
                if (cycle.isSignalStrengt()) result += cycle * x
                //println("Cycle $cycle x:" + input[cursorInput][1] + " value $x")
            }
        }
        println(result)
    }

    run part2@{
        var x = 1
        var cycle = 1
        input.forEach { instruction ->
            drawCRTPixel(x, cycle-1)
            cycle++

            if (instruction.size > 1) {
                drawCRTPixel(x, cycle-1)
                cycle++
                x += instruction[1].toInt()
            }
        }
    }
}


fun Int.isSignalStrengt(): Boolean = (this == 20 || this ==60 || this ==100 || this==140 || this==180 || this==220)
fun drawCRTPixel(x:Int, cycle:Int){
    if(cycle % 40 == 0) println()
    if(cycle % 40 in x-1..x+1)
        print("#")
    else
        print(".")
}