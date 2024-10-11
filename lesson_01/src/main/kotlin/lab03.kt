fun main() {
    first03()
}

fun first03() {
    val list = mutableListOf(1.7, 4.8, 2.9, 3.0)
    val map = mapOf("A" to 1, "B" to 2, "C" to 3, "E" to 4)

    list.sort()
    list.filter { it > 2.9 }
        .map { it * 3 }
        .forEach { println(it) }

    map.filter { it.value % 2 == 0 }
        .forEach { (key, value) -> println("Key $key with value $value") }
}
