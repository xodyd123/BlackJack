package com.taeyong.blackjack

import com.taeyong.blackjack.controller.GameController
import com.taeyong.blackjack.domain.dealear.Dealer
import com.taeyong.blackjack.domain.deck.RandomDeck
import com.taeyong.blackjack.domain.game.Game
import com.taeyong.blackjack.domain.player.Player
import com.taeyong.blackjack.domain.score.ScoreCalculator
import com.taeyong.blackjack.service.GameService
import com.taeyong.blackjack.view.ConsoleOutView
import com.taeyong.blackjack.view.InputView

fun main() {
    val game = Game(RandomDeck(), Player(ScoreCalculator()), Dealer(ScoreCalculator()))
    val scoreCalculator = ScoreCalculator()
    val gameController = GameController(
        ConsoleOutView, GameService(game),
        InputView
    )
    gameController.run()

}