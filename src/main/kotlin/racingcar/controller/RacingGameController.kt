package racingcar.controller

import racingcar.domain.Round
import racingcar.domain.condition.RandomCondition
import racingcar.service.RacingGameService
import racingcar.view.InputView
import racingcar.view.OutputView

class RacingGameController(
    private val racingGameService: RacingGameService
) {
    fun run() {
        OutputView.printRequestCarCount()
        val inputCarNames = InputView.inputName()
        OutputView.printRequestAttemptCount()
        val inputAttemptCount = InputView.inputNumber()
        OutputView.printExecutionResult()

        init(inputCarNames)
        play(Round(inputAttemptCount))
    }

    private fun init(carNames: List<String>) {
        racingGameService.initCars(carNames, RandomCondition)
    }

    private fun play(round: Round) {
        repeat(round.attemptCount) {
            val playCars = racingGameService.play()
            OutputView.printCarNameAndPosition(playCars)
        }
        val winners = racingGameService.getWinners()
        OutputView.printWinners(winners)
    }
}
