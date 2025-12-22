package com.taeyong.blackjack.domain.deck

import com.taeyong.blackjack.domain.card.Card
import com.taeyong.blackjack.domain.card.Rank
import com.taeyong.blackjack.domain.card.Suit

class RandomDeck() : Deck {

    private val cards = mutableListOf<Card>()

    init {
        initializeCards()
    }


    fun cards(): List<Card> {
        return cards.toList()
    }

    private fun initializeCards() {

        for (suit in Suit.values()) {
            for (rank in Rank.values()) {
                cards.add(Card(rank, suit))
            }
        }

        cards.shuffle()


    }

    override fun reset() {
        cards.clear()
        initializeCards()
    }

    override fun draw(): Card {
        check(cards.isNotEmpty()) { "덱에 카드가 비었습니다." }
        return cards.removeAt(0)

    }

    override fun size(): Int {
        return cards.size
    }

}