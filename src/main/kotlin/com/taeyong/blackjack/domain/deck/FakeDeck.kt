package com.taeyong.blackjack.domain.deck

import com.taeyong.blackjack.domain.card.Card


class FakeDeck(
    cards: List<Card>,
) : Deck {

    private val cards = cards.toMutableList()

    var drawCount: Int = 0
        private set

    override fun reset() {
        TODO("Not yet implemented")
    }

    override fun draw(): Card {
        check(cards.isNotEmpty()) { "더 이상 뽑을 카드가 없습니다." }
        drawCount++
        return cards.removeAt(0)
    }

    override fun size(): Int = cards.size
}