package com.taeyong.blackjack

import com.taeyong.blackjack.controller.GameController
import com.taeyong.blackjack.domain.card.Card
import com.taeyong.blackjack.domain.card.Rank
import com.taeyong.blackjack.domain.card.Suit
import com.taeyong.blackjack.domain.dealear.Dealer
import com.taeyong.blackjack.domain.deck.FakeDeck
import com.taeyong.blackjack.domain.deck.RandomDeck
import com.taeyong.blackjack.domain.game.Game
import com.taeyong.blackjack.domain.hand.Hand
import com.taeyong.blackjack.domain.player.Player
import com.taeyong.blackjack.domain.score.ScoreCalculator
import com.taeyong.blackjack.view.ConsoleOutView
import com.taeyong.blackjack.view.InputView
import com.taeyong.blackjack.view.mapper.ParticipantViewMapper

fun main() {
    val cards = listOf<Card>(
        Card(Rank.A, Suit.CLUB),
        Card(Rank.K, Suit.CLUB),
        Card(Rank.NINE, Suit.SPADE), // 20
        Card(Rank.NINE, Suit.HEART),
        Card(Rank.TWO, Suit.CLUB),
        Card(Rank.TWO, Suit.SPADE)
    )
    val deck = FakeDeck(cards)
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