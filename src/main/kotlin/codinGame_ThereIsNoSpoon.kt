import java.util.*
import java.io.*
import java.math.*

/**
 * Don't let the machines win. You are humanity's last hope...
 **/
fun main(args : Array<String>) {
    val width = 2 // the number of cells on the X axis
    val height = 2 // the number of cells on the Y axis
    val input = File("/Users/nbietry/IdeaProjects/AoC2022/inputs/input_ThereIsNoSpoon").readLines()
    val game = Array(height) { Array(width){""} }
    var inputLines = Array(height){CharArray(width){' '} }

    for (i in 0 until height) {
        for(j in 0 until width) {
            if(input[i].toCharArray().elementAt(j) != '.')
                println(j.toString() + " " + i.toString() + " " +
                        checkRight(input[i].toCharArray().getOrNull(j+1)) + " " +
                        checkDown(input.getOrNull(i+1)?.toCharArray()?.getOrNull(j))
                )
        }
    }
}

fun checkRight(input: Char?): String{
    return if(input == null || input == '.') "-1 -1"
    else "1 0"
}
fun checkDown(input: Char?): String{
    return if(input == null || input == '.') "-1 -1"
    else "0 1"
}