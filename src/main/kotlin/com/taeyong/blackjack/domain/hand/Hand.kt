package com.taeyong.blackjack.domain.hand

import com.taeyong.blackjack.domain.card.Card
import com.taeyong.blackjack.domain.score.ScoreCalculator

class Hand(private val calculator: ScoreCalculator) {

    private val _cards = mutableListOf<Card>()

    companion object {
        private const val BUST_LIMIT = 21
    }

    val score: Int
        get() = calculator.calculate(_cards)

    val size: Int
        get() = _cards.size

    fun add(card: Card) {
        _cards.add(card)
    }

    fun contains(card: Card): Boolean {
        return _cards.contains(card)
    }

    fun isBusted(): Boolean {
        return score > BUST_LIMIT
    }

}