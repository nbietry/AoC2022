import java.io.File

fun main(args: Array<String>) {

    val inputLines = File("/Users/nbietry/IdeaProjects/AoC2022/inputs/input7").readLines()

    val folderList = FolderList<String>()
    val root = folderList.createVertex("/")
    //folderList.add(EdgeType.UNDIRECTED, root, 0,0)
    val lastCommand = ""

    for(cmd in inputLines)
    {
        val cmdWord = cmd.split(" ")
    }

    println()
}

class FolderList<T> : Graph<T> {

    private val adjacencies: HashMap<Vertex<T>, ArrayList<Edge<T>>> = HashMap()

    override fun createVertex(data: T): Vertex<T> {
        val vertex = Vertex(adjacencies.count(), data)
        adjacencies[vertex] = ArrayList()
        return vertex
    }
    override fun addDirectedEdge(source: Vertex<T>, destination: Vertex<T>, weight: Double?) {
        val edge = Edge(source, destination, weight)
        adjacencies[source]?.add(edge)
    }
    override fun addUndirectedEdge(source: Vertex<T>, destination: Vertex<T>, weight: Double?) {
        addDirectedEdge(source, destination, weight)
        addDirectedEdge(destination, source, weight)
    }
    override fun edges(source: Vertex<T>) =
        adjacencies[source] ?: arrayListOf()

    override fun add(edge: EdgeType, source: Vertex<T>, destination: Vertex<T>, weight: Double?) {
        TODO("Not yet implemented")
    }

    override fun weight(source: Vertex<T>, destination: Vertex<T>): Double? {
        return edges(source).firstOrNull { it.destination == destination }?.weight
    }

    override fun toString(): String {
        return buildString { // 1
            adjacencies.forEach { (vertex, edges) -> // 2
                val edgeString = edges.joinToString { it.destination.data.toString() } // 3
                append("${vertex.data} ---> [ $edgeString ]\n") // 4
            }
        }
    }
}
data class Vertex<T>(val index: Int, val data: T)
data class Edge<T>(
    val source: Vertex<T>,
    val destination: Vertex<T>,
    val weight: Double? = null
)
enum class EdgeType {
    DIRECTED,
    UNDIRECTED
}
interface Graph<T> {
    fun createVertex(data: T): Vertex<T>
    fun addDirectedEdge(source: Vertex<T>, destination: Vertex<T>, weight: Double?)
    fun addUndirectedEdge(source: Vertex<T>, destination: Vertex<T>, weight: Double?)
    fun add(edge: EdgeType, source: Vertex<T>, destination: Vertex<T>, weight: Double?)
    fun edges(source: Vertex<T>): ArrayList<Edge<T>>
    fun weight(source: Vertex<T>, destination: Vertex<T>): Double?
}