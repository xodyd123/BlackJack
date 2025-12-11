package com.taeyong.blackjack.domain.dealear

import com.taeyong.blackjack.domain.card.Card
import com.taeyong.blackjack.domain.deck.Deck
import com.taeyong.blackjack.domain.hand.Hand

class Dealer(private val hand: Hand) {

    companion object {
        const val DEALER_STAND_SCORE = 17
    }

    val size: Int
        get() = hand.size

    val isBust: Boolean
        get() = hand.isBust

    val score: Int
        get() = hand.score

    val shouldHit: Boolean
        get() = DEALER_STAND_SCORE > hand.score

    val cards: List<Card>
        get() = hand.cardsSnapshot

    fun receive(card: Card) {
        hand.add(card)
    }

    fun playTurn(deck: Deck) {
        while (shouldHit) {
            receive(deck.draw())
        }
    }

}