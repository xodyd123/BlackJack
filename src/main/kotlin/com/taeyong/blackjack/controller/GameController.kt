package com.taeyong.blackjack.controller

import com.taeyong.blackjack.domain.game.RestartGameDecision
import com.taeyong.blackjack.domain.player.PlayerDecision
import com.taeyong.blackjack.service.GameService
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

            if (!playingPlayerTurn()) {
                playingDealerTurn()
                val judgeWinner = gameService.judgeWinner()
                outView.showGameResult(judgeWinner)

            }
            outView.restartGameDecisionPrompt()
            val decision = RestartGameDecision.fromInput(inputView.readLine())
            if (decision == RestartGameDecision.END) break
        }
    }

    private fun playingPlayerTurn(): Boolean {
        while (true) {
            outView.receiveCardPrompt()
            val input = inputView.readLine()
            val playerDecision = PlayerDecision.fromInput(input)
            when (playerDecision) {
                PlayerDecision.HIT -> {
                    outView.playerHitCardPrompt()
                    val playerGameRoundResult = gameService.playingPlayerTurn()
                    outView.printPlayerRound(playerGameRoundResult)
                    if (playerGameRoundResult.isBust) {
                        outView.playerBustPrompt()
                        return true
                    }
                }

                PlayerDecision.STAND -> {
                    return false
                }
            }
        }
    }

    private fun playingDealerTurn() {
        outView.dealerTurnStartPrompt()
        val playingDealerTurnResult = gameService.playingDealerTurn()
        outView.dealerTurnResult(playingDealerTurnResult)
    }

}