fun main() {
    val test = Test[1]
    println(test)
}

enum class Test(val code: Int){
    ME(1),OPP(0),FREE(-1);

    companion object {
        private val map = Ownership.values().associateBy { it.code }
        operator fun get(value: Int) = map[value]
    }
}