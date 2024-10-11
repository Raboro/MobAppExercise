fun main() {
    first02()
    second02()
}

fun first02() {
    val mutList = listOf("1", "2")
    println(mutList[1])
}

fun second02() {
    val mutMap = mutableMapOf<Int, String>()
    mutMap[1] = "1"
    mutMap[2] = "2"
    mutMap[3] = "3"

    mutMap.remove(2)
    println(mutMap[1])
}