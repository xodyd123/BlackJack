package com.taeyong.blackjack.domain.dealear

import com.taeyong.blackjack.domain.card.Card
import com.taeyong.blackjack.domain.deck.Deck
import com.taeyong.blackjack.domain.hand.Hand
import com.taeyong.blackjack.domain.score.ScoreCalculator
import com.taeyong.blackjack.domain.snapshot.CardView
import com.taeyong.blackjack.domain.snapshot.ParticipantSnapshot

class Dealer(private val calculator: ScoreCalculator) {

    private var hand = Hand(calculator)

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

    fun initialSnapshotHiddenScore(): ParticipantSnapshot {
        val cards = hand.cardsSnapshot
        val lastIndex = cards.lastIndex

        val cardsView = hand.cardsSnapshot.mapIndexed { index: Int, card: Card ->
            when (index) {
                lastIndex -> CardView.Hidden
                else -> CardView.Face(card.rank)
            }
        }
        return ParticipantSnapshot(cardsView, null, hand.isBust)
    }

    fun resetHand() {
        hand = Hand(calculator)
    }

    fun receive(card: Card) {
        hand.add(card)
    }

    fun playTurn(deck: Deck) {
        while (shouldHit) {
            receive(deck.draw())
        }
    }

}