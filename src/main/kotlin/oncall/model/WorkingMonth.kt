package oncall.model

import oncall.util.getNextPerson

data class WorkingMonth(
    val month: Month,
    val day: Day,
    val weekdaysPeople: List<String>,
    val weekendPeople: List<String>,
    private val timeTable: MutableList<String> = mutableListOf(),
) {
    private fun getTimeTable(): List<String> {
        var weekdaysIndex = 0
        var weekendIndex = 0
        var specialWeekdaysIndex = false
        var specialWeekendIndex = false
        val holidays = month.holiday ?: emptyList()
        var dayIndex = day.index

        for (workingDay in 1..month.days) {
            val changeIndex = dayIndex % 7
            val isHoliday = changeIndex == 0 || changeIndex == 6 || workingDay in holidays
            val person = when (isHoliday) {
                true -> weekendPeople.getNextPerson(weekendIndex)
                false -> weekdaysPeople.getNextPerson(weekdaysIndex)
            }
            if (timeTable.isEmpty() || person != timeTable.last()) {
                timeTable.add(person)

                if (isHoliday) {
                    weekendIndex++
                    if (specialWeekendIndex) {
                        weekendIndex++
                        specialWeekendIndex = false
                    }
                } else {
                    weekdaysIndex++
                    if (specialWeekdaysIndex) {
                        weekendIndex++
                        specialWeekdaysIndex = false
                    }
                }
            } else {
                val person = when (isHoliday) {
                    true -> {
                        val index = weekendIndex + 1
                        specialWeekendIndex = true
                        weekendPeople.getNextPerson(index)
                    }

                    false -> {
                        val index = weekdaysIndex + 1
                        specialWeekdaysIndex = true
                        weekdaysPeople.getNextPerson(index)
                    }
                }
                timeTable.add(person)
            }
            dayIndex++
        }
        return timeTable
    }

    fun showTimeTable(): List<String> {
        val workingTimeTable = mutableListOf<String>()
        getTimeTable()
        var dayIndex = day.index
        for (workingDay in 1..month.days) {
            val isHoliday = day.index == 0 || day.index == 6 || month.holiday?.contains(workingDay) == true
            val day = Day.entries.find { it.index == (dayIndex % 7) }
            val info = if (isHoliday && dayIndex in 1..5) {
                "${month.month}월 ${workingDay}일 ${day?.day}(휴일) ${timeTable[workingDay - 1]}"
            } else {
                "${month.month}월 ${workingDay}일 ${day?.day} ${timeTable[workingDay - 1]}"
            }
            workingTimeTable.add(info)
            dayIndex++
        }
        return workingTimeTable
    }
}
