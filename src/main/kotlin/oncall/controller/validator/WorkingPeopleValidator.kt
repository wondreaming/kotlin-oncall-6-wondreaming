package oncall.controller.validator

import oncall.model.Day
import oncall.util.splitByComma

class WorkingPeopleValidator {
    operator fun invoke(input: String) {
        checkEmpty(input)
        val people = input.split(",")
        for (person in people) {
            checkEmpty(person)
            checkMaxLength(person)
        }
        checkDuplicate(people)
    }

    private fun checkEmpty(input: String) {
        require(input.isNotEmpty()) { WorkingPeopleErrorType.EMPTY_INPUT }
    }

    private fun checkMaxLength(input: String) {
        require(input.length in 1..5) { WorkingPeopleErrorType.MAX_NICKNAME }
    }

    private fun checkDuplicate(input: List<String>) {
        require(input.size == input.toSet().size) { WorkingPeopleErrorType.NOT_DUPLICATE }
    }

    fun checkBetween(weekdaysPeople: String, weekendPeople: String) {
        val weekdaysWorkingPeople = weekdaysPeople.splitByComma()
        val weekendWorkingPeople = weekendPeople.splitByComma()
        val joinPeople = weekdaysWorkingPeople.union(weekendWorkingPeople)
        require(joinPeople.size in 5..35) { WorkingPeopleErrorType.BETWEEN }
    }
}