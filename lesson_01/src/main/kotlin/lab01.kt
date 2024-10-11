fun main() {
    first()
    second()
    third()
}

fun first() {
    val name: String = "Max Mustermann"
    println(name)
}

fun second() {
    var name = "Pete"
    name = "Maria"
    println(name)
}

fun third() {
    val number = 1.8
    println(number::class::simpleName)
    val number2 = 1.9f
    println(number2::class::simpleName)
}