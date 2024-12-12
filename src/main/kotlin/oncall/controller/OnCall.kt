package oncall.controller

import oncall.controller.domain.UserInteraction
import oncall.controller.validator.MonthAndDayValidator
import oncall.controller.validator.WorkingPeopleValidator
import oncall.model.Day
import oncall.model.Month
import oncall.model.WorkingMonth
import oncall.util.retryWhenNoException
import oncall.util.splitByComma

class OnCall(
    private val userInteraction: UserInteraction = UserInteraction(),
    private val monthAndDayValidator: MonthAndDayValidator = MonthAndDayValidator(),
    private val workingPeopleValidator: WorkingPeopleValidator = WorkingPeopleValidator(),
) {
    fun run() {
        val workingMonthAndDay = getWorkingMonthAndDay()
        val timeTable = workingMonthAndDay.showTimeTable()
        userInteraction.handleTimeTable(timeTable)
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

    private fun getWeekendPeople(): String {
        val weekendPeople = userInteraction.handleWeekendPeople()
        workingPeopleValidator(weekendPeople)
        return weekendPeople
    }

    private fun getWorkingPeople(): List<String> = retryWhenNoException{
        val weekdaysPeople = getWeekdaysPeople()
        val weekendPeople = getWeekendPeople()
        workingPeopleValidator.checkBetween(weekdaysPeople, weekendPeople)
        val workingPeople = listOf(weekdaysPeople, weekendPeople)
        workingPeople
    }

    private fun getWorkingMonthAndDay(): WorkingMonth {
        val monthAndDay = getMonthAndDay()
        val (month, day) = monthAndDay.splitByComma()
        val (weekdaysPeople, weekendPeople) = getWorkingPeople()
        val weekdaysWorkingPeople = weekdaysPeople.splitByComma()
        val weekendWorkingPeople = weekendPeople.splitByComma()
        val workingMonth = Month.entries.find { it.month == month.toInt() }
        val workingDay = Day.entries.find { it.day == day }
        val workingMonthAndDay = WorkingMonth(workingMonth!!, workingDay!!, weekdaysWorkingPeople, weekendWorkingPeople)
        return workingMonthAndDay
    }
}