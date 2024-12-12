package oncall.model

enum class Day(
    val index: Int,
    val day: String,
) {
    SUNDAY(0, "일"),
    MONDAY(1, "월"),
    TUESDAY(2, "화"),
    WEDNESDAY(3, "수"),
    THURSDAY(4, "목"),
    FRIDAY(5, "금"),
    SATURDAY(6, "토");
}