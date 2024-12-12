package oncall.controller.validator

import oncall.model.Day

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

    fun checkBetween(weekdaysPeople: List<String>, weekendPeople: List<String>) {
        val joinPeople = weekdaysPeople.union(weekendPeople)
        require(joinPeople.size in 5..35) { WorkingPeopleErrorType.BETWEEN }
    }
}