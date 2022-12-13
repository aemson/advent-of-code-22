import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt")
    .readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

/**
 * Splits list based on the predicate
 * */
fun <T> List<T>.split(predicate: (T) -> Boolean): List<List<T>> =
    this.indexOfFirst(predicate).let { idx ->
        return if (idx == -1) {
            listOf(this)
        } else {
            return listOf(this.take(idx)) + this.drop(idx + 1).split(predicate)
        }
    }
