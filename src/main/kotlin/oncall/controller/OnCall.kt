package oncall.controller

import oncall.controller.domain.UserInteraction

class OnCall(
    private val handleMonthAndDay: UserInteraction = UserInteraction(),
) {
    fun run() {
        val monthAndDay = getMonthAndDay()
    }

    private fun getMonthAndDay(): String {
        val monthAndDay = handleMonthAndDay.handleMonthAndDay()
        return monthAndDay
    }
}