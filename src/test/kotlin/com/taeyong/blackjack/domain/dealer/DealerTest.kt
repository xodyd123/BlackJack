package com.taeyong.blackjack.domain.dealer

import com.taeyong.blackjack.domain.card.Card
import com.taeyong.blackjack.domain.card.Rank
import com.taeyong.blackjack.domain.card.Suit
import com.taeyong.blackjack.domain.delear.Dealer
import com.taeyong.blackjack.domain.hand.Hand
import com.taeyong.blackjack.domain.score.ScoreCalculator
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DealerTest {

    @Test
    fun `딜러는_점수_총합이_21점을_초과하면_Bust이다`() {
        val dealer = Dealer(Hand(ScoreCalculator()))
        val card1 = Card(Rank.K, Suit.SPADE)
        val card2 = Card(Rank.J, Suit.SPADE)
        val card3 = Card(Rank.TWO, Suit.SPADE)
        dealer.receive(card1)
        dealer.receive(card2)
        dealer.receive(card3)

        assertTrue { dealer.isBust }
    }

    @Test
    fun `딜러는_점수_총합이_21점__미만이면_Bust이다`() {
        val dealer = Dealer(Hand(ScoreCalculator()))
        val card1 = Card(Rank.K, Suit.SPADE)
        val card2 = Card(Rank.J, Suit.SPADE)
        dealer.receive(card1)
        dealer.receive(card2)

        assertFalse { dealer.isBust }
    }

    @Test
    fun `딜러의 점수 총합이 17점 이상이면 카드를 받지 않는다`() {
        val dealer = Dealer(Hand(ScoreCalculator()))
        val card1 = Card(Rank.K, Suit.SPADE)
        val card2 = Card(Rank.J, Suit.SPADE)
        dealer.receive(card1)
        dealer.receive(card2)

        assertFalse { dealer.shouldHit }
    }

    @Test
    fun `딜러의 점수 총합이 17점 미만이면 카드를 받는다`() {
        val dealer = Dealer(Hand(ScoreCalculator()))
        val card1 = Card(Rank.K, Suit.SPADE)
        dealer.receive(card1)

        assertTrue { dealer.shouldHit }
    }
}