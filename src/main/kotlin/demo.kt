import java.util.*
import java.io.*
import java.math.*

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Game(val width: Int, val height: Int){
    var myMatter: Int = 0
    var oppMatter: Int = 0
    val battleground = Array(height){arrayOfNulls<Cell>(width)}
}
enum class Ownership(val code: Int){
    ME(1), OPP(0), FREE(-1);
    companion object {
        private val map = Ownership.values().associateBy { it.code }
        operator fun get(value: Int) = map[value]
    }
}
fun Int.toBoolean() = this == 1
fun debug(message: String){System.err.println(message)}
class Cell(
    val x: Int, val y: Int,
    private val scrapAmount: Int, owner: Int,
    private val units: Int, private val recycler: Int, canBuild: Int, canSpawn: Int,
    private val inRangeOfRecycler: Int
){
    private val owner = Ownership[owner]
    val canBuild = canBuild.toBoolean()
    val canSpawn = canSpawn.toBoolean()
    override fun toString(): String {
        return "($x,$y), Owmer: $owner, Recycler: $recycler, Can build: $canBuild, Can spawn: $canSpawn"
    }
}

fun main(args : Array<String>) {
    val input = Scanner(System.`in`)
    val game = Game(input.nextInt(), input.nextInt())

    // game loop
    while (true) {
        game.myMatter = input.nextInt()
        game.oppMatter = input.nextInt()
        for (i in 0 until game.height) {
            for (j in 0 until game.width) {
                game.battleground[i][j] = Cell(j, i, input.nextInt(), input.nextInt(), input.nextInt(), input.nextInt(), input.nextInt(), input.nextInt(), input.nextInt())
                //debug(game.battleground[i][j].toString())
            }
        }
        debug("Res: " + game.myMatter)
        println("WAIT")
    }
}

