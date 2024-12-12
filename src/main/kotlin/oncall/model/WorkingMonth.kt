package oncall.model

data class WorkingMonth(
    val month: Month,
    val day: Day,
    val weekdaysPeople: List<String>,
    val weekendPeople: List<String>,
    val timeTable: List<String>,
)
