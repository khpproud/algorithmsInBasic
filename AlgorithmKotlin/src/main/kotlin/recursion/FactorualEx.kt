package recursion

/**
 * This code snippet had inspired by medium article author 'Baseer Al-Obaidy''s
 * 'Understanding Recursion in Kotlin'
 * url : https://medium.com/@baseerhk/understanding-recursion-in-kotlin-part-i-factorials-optionals-and-list-operations-d78ec841b67d
 */

fun naiveFact(n: Int): Long = if (n <= 1) 1 else n * naiveFact(n - 1)

fun fact(n: Int): Long {
    fun inner(n: Int, acc: Long): Long = if (n <= 1) acc
        else inner(n - 1, n * acc)
    return inner(n, 1)
}

fun main() {
    val numbers = (1.. 20).toList()
    val facts1 = numbers.map(::naiveFact)
    val facts2 = numbers.map(::fact)
    // both factorial function will produce same results
    assert(facts1 == facts2)
    numbers.forEach { println("$it! = ${fact(it)}") }
}

