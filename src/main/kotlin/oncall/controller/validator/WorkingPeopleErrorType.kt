package oncall.controller.validator

enum class WorkingPeopleErrorType(
    private val errorMessage: String
) {
    EMPTY_INPUT("빈 문자를 입력했습니다."),
    NOT_DUPLICATE("닉네임 중복은 안됩니다."),
    MAX_NICKNAME("닉네임은 최대 5자만 가능합니다."),
    BETWEEN("비상 근무자 수는 5에서 35사이 입니다.");

    override fun toString(): String = "$ERROR $errorMessage"

    companion object {
        private const val ERROR = "[ERROR]"
    }
}