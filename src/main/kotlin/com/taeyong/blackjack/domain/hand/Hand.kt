package com.taeyong.blackjack.domain.hand

import com.taeyong.blackjack.domain.card.Card
import com.taeyong.blackjack.domain.score.ScoreCalculator

class Hand(private val calculator: ScoreCalculator) {

    private val cards = mutableListOf<Card>()

    fun add(card: Card) {
        cards.add(card)
    }

    fun size(): Int {
        return cards.size
    }

    fun contains(card: Card): Boolean {
        return cards.contains(card)
    }

    fun score(): Int {
        return calculator.calculate(cards)
    }
}