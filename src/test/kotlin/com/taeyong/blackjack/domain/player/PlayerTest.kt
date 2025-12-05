package com.taeyong.blackjack.domain.player

import com.taeyong.blackjack.domain.card.Card
import com.taeyong.blackjack.domain.card.Rank
import com.taeyong.blackjack.domain.card.Suit
import com.taeyong.blackjack.domain.hand.Hand
import com.taeyong.blackjack.domain.score.ScoreCalculator
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PlayerTest {

    @Test
    fun `플레이어는_카드_한장을_받으면_핸드에_추가된다`() {
        val hand = Hand(ScoreCalculator())
        val player = Player(hand)
        val card = Card(Rank.TWO, Suit.SPADE)
        player.receive(card)

        assertEquals(1, hand.size)
        assertTrue(hand.contains(card))


    }

    @Test
    fun `플레이어는_2와_4를_가지면_점수는_6이다`() {
        val hand = Hand(ScoreCalculator())
        val player = Player(hand)
        val card1 = Card(Rank.TWO, Suit.SPADE)
        val card2 = Card(Rank.FOUR, Suit.SPADE)
        player.receive(card1)
        player.receive(card2)

        assertEquals(6, player.score)
    }

    @Test
    fun `플레이어는_점수_총합이_21점을_초과하면_Bust이다`() {
        val hand = Hand(ScoreCalculator())
        val player = Player(hand)
        val card1 = Card(Rank.J, Suit.SPADE)
        val card2 = Card(Rank.K, Suit.SPADE)
        val card3 = Card(Rank.TWO, Suit.SPADE)
        player.receive(card1)
        player.receive(card2)
        player.receive(card3)

        assertTrue { player.isBust }
    }

    @Test
    fun `플레이어는_점수_총합이_21점_이하면_Bust가 아니다`() {
        val hand = Hand(ScoreCalculator())
        val player = Player(hand)
        val card1 = Card(Rank.J, Suit.SPADE)
        val card2 = Card(Rank.K, Suit.SPADE)
        player.receive(card1)
        player.receive(card2)


        assertFalse { player.isBust }
    }
}