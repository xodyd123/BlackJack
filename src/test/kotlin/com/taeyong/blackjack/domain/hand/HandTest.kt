package com.taeyong.blackjack.domain.hand

import com.taeyong.blackjack.domain.card.Card
import com.taeyong.blackjack.domain.card.Rank
import com.taeyong.blackjack.domain.card.Suit
import com.taeyong.blackjack.domain.score.ScoreCalculator
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class HandTest {

    @Test
    fun `핸드는_카드를_추가하면_그_카드를_보유하게_된다`() {
        val hand = Hand(ScoreCalculator())
        val card = Card(Rank.TWO, Suit.SPADE)
        hand.add(card)
        assertEquals(1, hand.size)
        assertTrue(hand.contains(card))

    }

    @Test
    fun `핸드는_가지고_있는_카드들의_점수의_총합을_계산할_수_있다`(){
        val hand = Hand(ScoreCalculator())
        val card1 = Card(Rank.TWO, Suit.SPADE)
        val card2 = Card(Rank.FOUR, Suit.SPADE)
        hand.add(card1)
        hand.add(card2)

        assertEquals(6, hand.score)

    }

    @Test
    fun `핸드는_점수_총합이_21점을_초과하면_BUST_상태가_된다`() {
        val hand = Hand(ScoreCalculator())
        val card1 = Card(Rank.J, Suit.SPADE)
        val card2 = Card(Rank.K, Suit.SPADE)
        val card3 = Card(Rank.TWO, Suit.SPADE)
        hand.add(card1)
        hand.add(card2)
        hand.add(card3)

        assertEquals(22, hand.score)
        assertTrue { hand.isBusted() }

    }

    @Test
    fun `핸드는_점수_총합이_21점_이하면_BUST_상태가_아니다`() {
        val hand = Hand(ScoreCalculator())
        val card1 = Card(Rank.J, Suit.SPADE)
        val card2 = Card(Rank.K, Suit.SPADE)

        hand.add(card1)
        hand.add(card2)


        assertEquals(20, hand.score)
        assertFalse { hand.isBusted() }

    }

}