package com.taeyong.blackjack.domain.dealer

import com.taeyong.blackjack.domain.card.Card
import com.taeyong.blackjack.domain.card.Rank
import com.taeyong.blackjack.domain.card.Suit
import com.taeyong.blackjack.domain.deck.FakeDeck
import com.taeyong.blackjack.domain.dealear.Dealer
import com.taeyong.blackjack.domain.score.ScoreCalculator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test


class DealerTest {

    @Test
    fun `딜러는_점수_총합이_21점을_초과하면_Bust이다`() {
        val dealer = Dealer(ScoreCalculator())
        val card1 = Card(Rank.K, Suit.SPADE)
        val card2 = Card(Rank.J, Suit.SPADE)
        val card3 = Card(Rank.TWO, Suit.SPADE)
        dealer.initialReceive(card1)
        dealer.initialReceive(card2)
        dealer.initialReceive(card3)

        assertTrue { dealer.isBust }
    }

    @Test
    fun `딜러는_점수_총합이_21점_미만이면_Bust가 아니다`() {
        val dealer = Dealer(ScoreCalculator())
        val card1 = Card(Rank.K, Suit.SPADE)
        val card2 = Card(Rank.J, Suit.SPADE)
        dealer.initialReceive(card1)
        dealer.initialReceive(card2)

        assertFalse { dealer.isBust }
    }

    @Test
    fun `딜러는_점수_총합이_정확히_21점이면_Bust가_아니다`() {
        val dealer = Dealer(ScoreCalculator())
        dealer.initialReceive(Card(Rank.K, Suit.SPADE))
        dealer.initialReceive(Card(Rank.A, Suit.HEART))

        assertFalse(dealer.isBust)
    }


    @Test
    fun `딜러의_점수_총합이_17점_이상이면_카드를_받지_않는다`() {
        val dealer = Dealer(ScoreCalculator())
        val card1 = Card(Rank.K, Suit.SPADE)
        val card2 = Card(Rank.J, Suit.SPADE)
        dealer.initialReceive(card1)
        dealer.initialReceive(card2)

        assertFalse { dealer.shouldHit }
    }

    @Test
    fun `딜러의_점수_총합이_정확히_17점_이면_카드를_받지_않는다`() {
        val dealer = Dealer(ScoreCalculator())
        val card1 = Card(Rank.K, Suit.SPADE)
        val card2 = Card(Rank.SEVEN, Suit.SPADE)
        dealer.initialReceive(card1)
        dealer.initialReceive(card2)

        assertFalse { dealer.shouldHit }
    }

    @Test
    fun `딜러의_점수_총합이_17점 미만이면_카드를_받는다`() {
        val dealer = Dealer(ScoreCalculator())
        val card1 = Card(Rank.K, Suit.SPADE)
        dealer.initialReceive(card1)

        assertTrue { dealer.shouldHit }
    }

    @Test
    fun `딜러는_점수_총합이_17점_이상이면_playTurn_동안_카드를_받지_않는다`() {
        val dealer = Dealer(ScoreCalculator())

        dealer.initialReceive(Card(Rank.K, Suit.SPADE))
        dealer.initialReceive(Card(Rank.SEVEN, Suit.HEART))

        val fakeDeck = FakeDeck(
            listOf(
                Card(Rank.TWO, Suit.CLUB),
                Card(Rank.THREE, Suit.DIAMOND)
            )
        )

        val beforeSize = dealer.size
        dealer.playTurn(fakeDeck, mutableListOf())

        assertEquals(beforeSize, dealer.size)
        assertEquals(0, fakeDeck.drawCount)
    }

    @Test
    fun `딜러는_점수_총합이_17점_미만이면_playTurn_동안_자동으로_카드를_받는다`() {
        val dealer = Dealer(ScoreCalculator())

        dealer.initialReceive(Card(Rank.K, Suit.SPADE))
        dealer.initialReceive(Card(Rank.SIX, Suit.HEART))

        val fakeDeck = FakeDeck(
            listOf(
                Card(Rank.TWO, Suit.CLUB),
            )
        )
        dealer.playTurn(fakeDeck, mutableListOf())

        assertEquals(3, dealer.size)
        assertEquals(18, dealer.score)
        assertEquals(1, fakeDeck.drawCount)

    }

}