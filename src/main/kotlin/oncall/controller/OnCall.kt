package oncall.controller

import oncall.controller.domain.UserInteraction
import oncall.controller.validator.MonthAndDayValidator
import oncall.controller.validator.WorkingPeopleValidator
import oncall.util.retryWhenNoException
import oncall.util.splitByComma

class OnCall(
    private val userInteraction: UserInteraction = UserInteraction(),
    private val monthAndDayValidator: MonthAndDayValidator = MonthAndDayValidator(),
    private val workingPeopleValidator: WorkingPeopleValidator = WorkingPeopleValidator(),
) {
    fun run() {
        val monthAndDay = getMonthAndDay()
        val (month, day) = monthAndDay.splitByComma()
        val weekdaysPeople = getWeekdaysPeople()
    }

    private fun getMonthAndDay(): String = retryWhenNoException {
        val monthAndDay = userInteraction.handleMonthAndDay()
        monthAndDayValidator(monthAndDay)
        monthAndDay
    }

    private fun getWeekdaysPeople(): String = retryWhenNoException {
        val weekdaysPeople = userInteraction.handleWeekdaysPeople()
        workingPeopleValidator(weekdaysPeople)
        weekdaysPeople
    }
}