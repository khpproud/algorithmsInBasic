package recursion

/**
 * This code snippet had inspired by medium article author 'Baseer Al-Obaidy''s
 * 'Understanding Recursion in Kotlin'
 * url : https://medium.com/@baseerhk/understanding-recursion-in-kotlin-part-i-factorials-optionals-and-list-operations-d78ec841b67d
 */

fun main() {
    val nullableList: List<Int?> = listOf(1, 2, 3, 5, null, 7)

    // null-safe call syntax
    nullableList.map { it?.times(2) }.forEach { println(it) }
}