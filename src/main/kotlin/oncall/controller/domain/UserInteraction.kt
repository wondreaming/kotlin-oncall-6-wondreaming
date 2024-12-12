package oncall.controller.domain

import oncall.view.InputView
import oncall.view.OutputView

class UserInteraction(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    fun handleMonthAndDay(): String {
        outputView.showQuestion("비상 근무를 배정할 월과 시작 요일을 입력하세요> ")
        return inputView.getInput()
    }
}