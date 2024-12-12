package oncall.controller

import oncall.controller.domain.UserInteraction
import oncall.controller.validator.MonthAndDayValidator
import oncall.util.retryWhenNoException
import oncall.util.splitByComma

class OnCall(
    private val userInteraction: UserInteraction = UserInteraction(),
    private val monthAndDayValidator: MonthAndDayValidator = MonthAndDayValidator(),
) {
    fun run() {
        val monthAndDay = getMonthAndDay()
        val (month, day) = monthAndDay.splitByComma()
    }

    private fun getMonthAndDay(): String = retryWhenNoException{
        val monthAndDay = userInteraction.handleMonthAndDay()
        monthAndDayValidator(monthAndDay)
        monthAndDay
    }
}