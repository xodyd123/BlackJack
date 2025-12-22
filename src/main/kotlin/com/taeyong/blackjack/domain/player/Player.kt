package com.taeyong.blackjack.domain.player

import com.taeyong.blackjack.domain.card.Card
import com.taeyong.blackjack.domain.deck.Deck
import com.taeyong.blackjack.domain.hand.Hand
import com.taeyong.blackjack.domain.score.ScoreCalculator
import com.taeyong.blackjack.domain.snapshot.CardView
import com.taeyong.blackjack.domain.snapshot.ParticipantSnapshot

class Player(private val calculator: ScoreCalculator) {

    private var hand = Hand(calculator)

    fun resetHand() {
        hand = Hand(calculator)
    }

    fun snapShot(): ParticipantSnapshot {
        val cardsView = hand.cardsSnapshot.map { card -> CardView.Face(card.rank) }
        val score = hand.score
        val isBusted = hand.isBust

        return ParticipantSnapshot(cardsView, score, isBusted)
    }

    val score: Int
        get() = hand.score

    val isBust: Boolean
        get() = hand.isBust

    val size: Int
        get() = hand.size

    val cards: List<Card>
        get() = hand.cardsSnapshot

    fun receive(card: Card) {
        hand.add(card)
    }

    fun hit(deck: Deck) {
        hand.add(deck.draw())
    }


}