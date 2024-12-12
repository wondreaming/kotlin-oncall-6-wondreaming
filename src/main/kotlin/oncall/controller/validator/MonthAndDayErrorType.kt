package oncall.controller.validator

enum class MonthAndDayErrorType(
    private val errorMessage: String
) {
    EMPTY_INPUT("빈 문자를 입력했습니다."),
    NOT_INTEGER("월에 long 타입의 숫자나 문자 타입을 입력했습니다."),
    BETWEEN("1~12사이의 숫자를 입력해주세요."),
    DAY("일, 월, 화, 수, 목, 금, 토만 입력해주세요.");

    override fun toString(): String = "$ERROR $errorMessage"

    companion object {
        private const val ERROR = "[ERROR]"
    }
}