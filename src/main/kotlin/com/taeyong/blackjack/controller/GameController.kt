package com.taeyong.blackjack.controller

import com.taeyong.blackjack.domain.game.Game
import com.taeyong.blackjack.view.OutView

class GameController(private val outView: OutView, private val game: Game) {

    fun run() {
        outView.startPrompt()
        val currentResult = game.start()
        outView.playerCardResult(currentResult)
    }
}