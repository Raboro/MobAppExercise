fun main() {
    println(merge("Tim", 20))
    println(mergeAsLambda("Tim", 20))
}

fun merge(name: String, age: Int): String {
    return "$name with age: $age"
}

val mergeAsLambda: (String, Int) -> String = { name, age -> "$name with age: $age" }