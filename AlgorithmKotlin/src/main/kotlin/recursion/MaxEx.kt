package recursion

/**
 * This code snippet had inspired by medium article author 'Baseer Al-Obaidy''s
 * 'Understanding Recursion in Kotlin'
 * url : https://medium.com/@baseerhk/understanding-recursion-in-kotlin-part-i-factorials-optionals-and-list-operations-d78ec841b67d
 */

// Find the maximum value in list which allows null value!!
// Below 'where' phrase means the elements of the list passed to 'max()' must be
// of some type which implements the 'Comparable' interface
fun <E> max(list: List<E>): E? where E : Comparable<E> {
    // 'tail-call optimization' -> 'TCO' style inner function
    // The local 'inner()' function expects a list and a initial max value
    fun inner(l: List<E>, m: E): E {
        return when {
            // 'base case' : if list is empty, simply returns the initial max value
            l.isEmpty() -> m
            // compares the initial value to the head of the list(first element in list)
            // the local function then calls itself passing the tail of the list(drop(1) -> minus first element)
            m < l[0] -> inner(l.drop(1), l[0])
            else -> inner(l.drop(1), m)
        }
    }

    // If the input list is empty, the outer function(max()) returns null,
    // otherwise, it calls its local function(inner()) passing the tail of the list(the initial list)
    // and its head(the initial maximum value) as initial value
    return if (list.isEmpty()) null else inner(list.drop(1), list[0])
}

// Optional approach(based via Java8 Optional Class)
sealed class Optional<E> {
    override fun toString(): String {
        return when (this) {
            is None -> "None"
            is Some -> value.toString()
        }
    }
}

class None<E> : Optional<E>()
class Some<E>(val value: E) : Optional<E>()

// Alternatively no nulls allowed in function
fun <E> maxNoNulls(list: List<E>): Optional<E> where E : Comparable<E> {
    fun inner(l: List<E>, m: E): Optional<E> {
        return when {
            l.isEmpty() -> Some(m)  // base case
            m < l[0] -> inner(l.drop(1), l[0])
            else -> inner(l.drop(1), m)
        }
    }
    return if (list.isEmpty()) None() else inner(list.drop(1), list[0])
}

fun main() {
    fun <E> listInMax(list: List<E>): String where E : Comparable<E> {
        val m = maxNoNulls(list)
        return "The maximum of $list is: ${when(m) {
            is None<E> -> "empty -> No maximum value"
            is Some<E> -> m.value    
        }}\n"
    }

    println(listInMax(listOf<Int>(1, 3, 5, 0, 14, 3, 7, 9)))
    println(listInMax(listOf<Int>()))
}