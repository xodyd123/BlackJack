package com.taeyong.blackjack.controller

import com.taeyong.blackjack.domain.game.RestartGameDecision
import com.taeyong.blackjack.service.GameService
import com.taeyong.blackjack.view.InputView
import com.taeyong.blackjack.view.OutView



class GameController(
    private val outView: OutView,
    private val gameService: GameService,
    private val inputView: InputView
) {
    fun run() {
        while (true){
            outView.startPrompt()
            val startRoundResult = gameService.startRound()
            outView.printInitialRound(startRoundResult)


            val decision = RestartGameDecision.fromInput(inputView.readLine())
            if (decision == RestartGameDecision.END) break
        }
    }

}