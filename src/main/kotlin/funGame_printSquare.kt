fun main(args : Array<String>) {
    val HEIGHT = 10
    val WIDTH = 10
    val C = '*'
    val CV = '-'
    val CH = '|'

    for(cursor in 1..WIDTH*HEIGHT) {
        if (cursor == 1 || cursor == WIDTH || cursor == WIDTH*HEIGHT-WIDTH+1 || cursor == (WIDTH*HEIGHT)) print(C)
        else if (cursor < WIDTH || cursor > (WIDTH*HEIGHT)-WIDTH) print(CV)
        else if (cursor % WIDTH == 1 || cursor % WIDTH == 0) print(CH)
        else print(" ")
        if(cursor % WIDTH==0) println()
    }
}
