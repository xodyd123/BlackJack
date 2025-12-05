package com.taeyong.blackjack.domain.deck

import com.taeyong.blackjack.domain.card.Card
import com.taeyong.blackjack.domain.card.Rank
import com.taeyong.blackjack.domain.card.Suit

class Deck() {

    private var cards = mutableListOf<Card>()

    init {
        initializeCards()
    }

    private fun initializeCards() {
        for (suit in Suit.values()) {
            for (rank in Rank.values()) {
                cards.add(Card(rank, suit))
            }
        }

        cards.shuffle()
    }

    fun size(): Int {
        return cards.size
    }

    fun cards(): List<Card> {
        return cards.toList()
    }

    fun draw(): Card {
        check(cards.isNotEmpty()) { "덱에 카드가 비었습니다." }
        return cards.removeAt(0)
    }

}