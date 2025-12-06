package com.taeyong.blackjack.domain.dealer

import com.taeyong.blackjack.domain.card.Card
import com.taeyong.blackjack.domain.card.Rank
import com.taeyong.blackjack.domain.card.Suit
import com.taeyong.blackjack.domain.deck.FakeDeck
import com.taeyong.blackjack.domain.delear.Dealer
import com.taeyong.blackjack.domain.hand.Hand
import com.taeyong.blackjack.domain.score.ScoreCalculator
import org.junit.jupiter.api.Assertions.assertEquals
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
    fun `딜러는_점수_총합이_21점_미만이면_Bust가 아니다`() {
        val dealer = Dealer(Hand(ScoreCalculator()))
        val card1 = Card(Rank.K, Suit.SPADE)
        val card2 = Card(Rank.J, Suit.SPADE)
        dealer.receive(card1)
        dealer.receive(card2)

        assertFalse { dealer.isBust }
    }

    @Test
    fun `딜러는_점수_총합이_정확히_21점이면_Bust가_아니다`() {
        val dealer = Dealer(Hand(ScoreCalculator()))
        dealer.receive(Card(Rank.K, Suit.SPADE))
        dealer.receive(Card(Rank.A, Suit.HEART))

        assertFalse(dealer.isBust)
    }


    @Test
    fun `딜러의_점수_총합이_17점_이상이면_카드를_받지_않는다`() {
        val dealer = Dealer(Hand(ScoreCalculator()))
        val card1 = Card(Rank.K, Suit.SPADE)
        val card2 = Card(Rank.J, Suit.SPADE)
        dealer.receive(card1)
        dealer.receive(card2)

        assertFalse { dealer.shouldHit }
    }

    @Test
    fun `딜러의_점수_총합이_정확히_17점_이면_카드를_받지_않는다`() {
        val dealer = Dealer(Hand(ScoreCalculator()))
        val card1 = Card(Rank.K, Suit.SPADE)
        val card2 = Card(Rank.SEVEN, Suit.SPADE)
        dealer.receive(card1)
        dealer.receive(card2)

        assertFalse { dealer.shouldHit }
    }

    @Test
    fun `딜러의_점수_총합이_17점 미만이면_카드를_받는다`() {
        val dealer = Dealer(Hand(ScoreCalculator()))
        val card1 = Card(Rank.K, Suit.SPADE)
        dealer.receive(card1)

        assertTrue { dealer.shouldHit }
    }

    @Test
    fun `딜러는_점수_총합이_17점_이상이면_playTurn_동안_카드를_받지_않는다`() {
        val hand = Hand(ScoreCalculator())
        val dealer = Dealer(hand)

        dealer.receive(Card(Rank.K, Suit.SPADE))
        dealer.receive(Card(Rank.SEVEN, Suit.HEART))

        val fakeDeck = FakeDeck(
            listOf(
                Card(Rank.TWO, Suit.CLUB),
                Card(Rank.THREE, Suit.DIAMOND)
            )
        )

        val beforeSize = hand.size
        dealer.playTurn(fakeDeck)

        assertEquals(beforeSize, hand.size)
        assertEquals(0, fakeDeck.drawCount)
    }
}