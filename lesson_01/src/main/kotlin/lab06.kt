import kotlin.properties.Delegates.vetoable


fun main() {
    var str: String by vetoable("a") { _, _, new -> new == "a" || new == "b" }
    str = "b"
    println(str)
    str = "c"
    println(str)
    str = "a"
    println(str)
}

