package com.taeyong.blackjack.controller

import com.taeyong.blackjack.domain.dealear.Dealer.Companion.DEALER_STAND_SCORE
import com.taeyong.blackjack.domain.game.Game
import com.taeyong.blackjack.domain.game.RestartGameDecision
import com.taeyong.blackjack.domain.player.PlayerDecision
import com.taeyong.blackjack.service.GameService
import com.taeyong.blackjack.view.InputView
import com.taeyong.blackjack.view.OutView
import com.taeyong.blackjack.view.mapper.ParticipantViewMapper


class GameController(
    private val outView: OutView,
    private val gameService: GameService,
    private val inputView: InputView
) {
    fun run() {
        while (true){
            outView.startPrompt()
            val startRoundResult = gameService.startRound()
        }


    }

}