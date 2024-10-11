fun main() {
    first04()
}

fun first04() {
    (1..10).forEach {
        println(it)
        println(if (it % 2 == 0) "even" else "odd")
    }
}