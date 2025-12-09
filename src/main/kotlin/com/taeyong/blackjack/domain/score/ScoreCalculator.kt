package com.taeyong.blackjack.domain.score

import com.taeyong.blackjack.domain.card.Card
import com.taeyong.blackjack.domain.card.Rank
import com.taeyong.blackjack.domain.hand.Hand

class ScoreCalculator {

    fun calculate(cards: List<Card>): Int {

        val (aces, others) = cards.partition { it.rank == Rank.A }

        var sum = others.sumOf { it.rank.baseScoreIgnoringAce }

        sum += aces.size * 11

        var softAces = aces.size

        while (sum > Hand.BUST_LIMIT && softAces > 0) {
            sum -= 10
            softAces--
        }
        return sum
    }


}