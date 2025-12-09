package com.taeyong.blackjack.domain.hand

import com.taeyong.blackjack.domain.card.Card
import com.taeyong.blackjack.view.dto.PlayerDto
import com.taeyong.blackjack.domain.score.ScoreCalculator

class Hand(private val calculator: ScoreCalculator) {

    private val _cards = mutableListOf<Card>()

    companion object {
        const val BUST_LIMIT = 21
    }

    val score: Int
        get() = calculator.calculate(_cards)

    val size: Int
        get() = _cards.size

    val isBust: Boolean
        get() = score > BUST_LIMIT

    val cardsSnapshot: List<Card>
        get() = _cards.toList()

    fun add(card: Card) {
        _cards.add(card)
    }

    fun contains(card: Card): Boolean {
        return _cards.contains(card)
    }

    fun cardResultDto(): PlayerDto {
        val cardNumbers = _cards.map { card -> calculator.baseScoreIgnoringAce(card.rank) }
        return PlayerDto(score, cardNumbers)

    }


}