package com.taeyong.blackjack

import com.taeyong.blackjack.controller.GameController
import com.taeyong.blackjack.domain.dealear.Dealer
import com.taeyong.blackjack.domain.deck.RandomDeck
import com.taeyong.blackjack.domain.game.Game
import com.taeyong.blackjack.domain.hand.Hand
import com.taeyong.blackjack.domain.player.Player
import com.taeyong.blackjack.domain.score.ScoreCalculator
import com.taeyong.blackjack.view.ConsoleOutView
import com.taeyong.blackjack.view.InputView
import com.taeyong.blackjack.view.mapper.ParticipantViewMapper

fun main() {
    val game = Game(RandomDeck())
    val scoreCalculator = ScoreCalculator()
    val gameController = GameController(
        ConsoleOutView, game,
        ParticipantViewMapper(scoreCalculator),
        Player(Hand(scoreCalculator)),
        Dealer(Hand(scoreCalculator)),
        InputView
    )
    gameController.run()

}