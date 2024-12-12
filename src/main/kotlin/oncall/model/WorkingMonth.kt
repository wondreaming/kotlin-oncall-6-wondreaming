package oncall.model

data class WorkingMonth(
    val month: Month,
    val day: Day,
    val weekdaysPeople: List<String>,
    val weekendPeople: List<String>,
    private val timeTable: MutableList<String> = mutableListOf(),
)
