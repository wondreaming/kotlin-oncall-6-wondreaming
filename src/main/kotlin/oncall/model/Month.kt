package oncall.model

enum class Month(
    val month: Int,
    val days: Int,
    val holiday: List<Int>?
) {
    JANUARY(1, 31, listOf(1)),
    FEBRUARY(2,28, null),
    MARCH(3, 31, listOf(1)),
    APRIL(4, 30, null),
    MAY(5, 31, listOf(5)),
    JUNE(6, 30, listOf(6)),
    JULY(7, 31, null),
    AUGUST(8, 31, listOf(15)),
    SEPTEMBER(9, 30, null),
    OCTOBER(10, 31, listOf(3, 9)),
    NOVEMBER(11, 30, null),
    DECEMBER(12, 31, listOf(25));
}