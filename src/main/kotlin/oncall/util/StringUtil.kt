package oncall.util

import java.text.NumberFormat
import java.util.*

fun <T> retryWhenNoException(action: () -> T): T {
    while (true) {
        try {
            return action.invoke()
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }
}

fun String.splitByComma() = this.split(",").filter { it.isNotBlank() }.map { it.trim() }
fun Int.toKoreanUnit(): String = NumberFormat.getNumberInstance(Locale.KOREAN).format(this)
fun Double.convertRoundAtTwoDecimal(): String = "%.1f".format(this)
fun List<String>.getNextPerson(index: Int): String {
    return this[index % this.size]
}