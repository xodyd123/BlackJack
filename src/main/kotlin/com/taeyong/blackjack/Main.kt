package com.taeyong.blackjack

import com.taeyong.blackjack.controller.GameController
import com.taeyong.blackjack.domain.card.Card
import com.taeyong.blackjack.domain.card.Rank
import com.taeyong.blackjack.domain.card.Suit
import com.taeyong.blackjack.domain.dealear.Dealer
import com.taeyong.blackjack.domain.deck.FakeDeck
import com.taeyong.blackjack.domain.game.Game
import com.taeyong.blackjack.domain.hand.Hand
import com.taeyong.blackjack.domain.player.Player
import com.taeyong.blackjack.domain.score.ScoreCalculator
import com.taeyong.blackjack.view.ConsoleOutView
import com.taeyong.blackjack.view.InputView
import com.taeyong.blackjack.view.mapper.ParticipantViewMapper

fun main() {
    val cards = listOf<Card>(
        Card(Rank.K, Suit.SPADE),
        Card(Rank.Q, Suit.SPADE),
        Card(Rank.J, Suit.SPADE),
        Card(Rank.THREE, Suit.HEART),
        Card(Rank.EIGHT, Suit.DIAMOND),
    )
    val deck = FakeDeck(cards)
    val game = Game(deck)
    val scoreCalculator = ScoreCalculator()
    val gameController = GameController(
        ConsoleOutView, game,
        ParticipantViewMapper(scoreCalculator),
        Player(Hand(scoreCalculator)),
        Dealer(Hand(scoreCalculator)),
        InputView)
    gameController.run()

}