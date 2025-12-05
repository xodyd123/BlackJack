package com.taeyong.blackjack.domain.delear

import com.taeyong.blackjack.domain.card.Card
import com.taeyong.blackjack.domain.hand.Hand

class Dealer(private val hand: Hand) {

    companion object {
        private const val DEALER_STAND_SCORE = 17
    }

    val isBust: Boolean
        get() = hand.isBust

    val shouldHit: Boolean
        get() = DEALER_STAND_SCORE > hand.score

    fun receive(card: Card) {
        hand.add(card)
    }

}