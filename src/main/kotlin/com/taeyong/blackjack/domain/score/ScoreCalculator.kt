package com.taeyong.blackjack.domain.score

import com.taeyong.blackjack.domain.card.Card
import com.taeyong.blackjack.domain.card.Rank
import com.taeyong.blackjack.domain.hand.Hand

class ScoreCalculator {

    fun calculate(cards: List<Card>): Int {

        val (aces, others) = cards.partition { it.rank == Rank.A }

        var sum = others.sumOf { baseScoreIgnoringAce(it.rank) }

        sum += aces.size * 11

        var softAces = aces.size

        while (sum > Hand.BUST_LIMIT && softAces > 0) {
            sum -= 10
            softAces--
        }
        return sum
    }

    fun baseScoreIgnoringAce(rank: Rank): Int =
        when (rank) {
            Rank.TWO -> 2
            Rank.THREE -> 3
            Rank.FOUR -> 4
            Rank.FIVE -> 5
            Rank.SIX -> 6
            Rank.SEVEN -> 7
            Rank.EIGHT -> 8
            Rank.NINE -> 9
            Rank.TEN,
            Rank.J,
            Rank.Q,
            Rank.K -> 10

            Rank.A -> 0
        }

}