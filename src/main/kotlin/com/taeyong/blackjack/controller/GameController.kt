package com.taeyong.blackjack.controller

import com.taeyong.blackjack.domain.game.RestartGameDecision
import com.taeyong.blackjack.service.GameService
import com.taeyong.blackjack.service.StepResult
import com.taeyong.blackjack.view.InputView
import com.taeyong.blackjack.view.OutView

class GameController(
    private val outView: OutView,
    private val gameService: GameService,
    private val inputView: InputView
) {
    fun run() {
        while (true) {
            outView.startPrompt()
            val startRoundResult = gameService.startRound()
            outView.printInitialRound(startRoundResult)

            while (true) {
                outView.receiveCardPrompt()
                val input = inputView.readLine()

                when (val step = gameService.nextStep(input)) {
                    is StepResult.PlayerUpdated -> outView.printPlayerRound(step.player)
                    is StepResult.DealerUpdated -> {
                        outView.dealerTurnResult(step.dealer, step.result)
                        break
                    }

                    is StepResult.RoundEnded -> {
                        outView.printPlayerRound(step.player)
                        break
                    }
                }
            }

            outView.restartGameDecisionPrompt()
            val decision = RestartGameDecision.fromInput(inputView.readLine())
            if (decision == RestartGameDecision.END) break
        }
    }

}