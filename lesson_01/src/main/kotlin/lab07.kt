fun main() {
    val person1 = Person("P", 1)
    var copy = person1.copy(name = "P2")
    val person2 = Person2("P3", 2)
    println(person2.hello());
}

data class Person(val name: String, val age: Int)

class Person2(val name: String, val age: Int) {
    fun hello() = "Hello I`m $name"
}