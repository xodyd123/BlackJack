package com.taeyong.blackjack.domain.player

import com.taeyong.blackjack.domain.card.Card
import com.taeyong.blackjack.domain.deck.Deck
import com.taeyong.blackjack.domain.hand.Hand

class Player(private val hand: Hand) {

    val score: Int
        get() = hand.score

    val isBust: Boolean
        get() = hand.isBust

    fun receive(card: Card) {
        hand.add(card)
    }

    fun hit(deck: Deck){
        hand.add(deck.draw())
    }


}