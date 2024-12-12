package oncall.controller.validator

import oncall.model.Day

class MonthAndDayValidator {
    operator fun invoke(input: String) {
        checkEmpty(input)
        val (month, day) = input.split(",")
        checkEmpty(month)
        checkEmpty(day)
        checkInteger(month)
        checkBetween(month)
        checkDay(day)
    }

    private fun checkEmpty(input: String) {
        require(input.isNotEmpty()) { MonthAndDayErrorType.EMPTY_INPUT }
    }

    private fun checkInteger(input: String) {
        requireNotNull(input.toIntOrNull()) { MonthAndDayErrorType.NOT_INTEGER }
    }

    private fun checkBetween(input: String) {
        require(input.toInt() in 1..12) { MonthAndDayErrorType.BETWEEN }
    }

    private fun checkDay(input: String) {
        require(Day.entries.any { it.day == input }) { MonthAndDayErrorType.DAY }
    }
}