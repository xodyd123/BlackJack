package com.taeyong.blackjack.domain.deck

import com.taeyong.blackjack.domain.card.Rank
import com.taeyong.blackjack.domain.card.Suit
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals

class DeckTest {

    @Test
    fun `덱에는_초기에_52장의_카드가_들어있다`() {
        val randomDeck = RandomDeck()
        val deckSize = randomDeck.size()

        assertEquals(52, deckSize)
    }

    @Test
    fun `각_랭크는_덱에_4장씩_존재한다`() {
        val randomDeck = RandomDeck()
        val cards = randomDeck.cards()
        val ranks = cards.groupBy { it -> it.rank }
        Rank.values().forEach { rank ->
            val count = ranks[rank]?.size ?: 0
            assertEquals(4, count)
        }
    }

    @Test
    fun `각_SUIT는_덱에_13장씩 존재한다`() {
        val randomDeck = RandomDeck()
        val cards = randomDeck.cards()
        val suits = cards.groupBy { it -> it.suit }
        Suit.values().forEach { suit ->
            val count = suits[suit]?.size ?: 0
            assertEquals(13, count)
        }
    }

    @Test
    fun `덱에서_뽑은_52장의_카드는_모두_서로_다르다`(){
        val randomDeck = RandomDeck()
        val cards = randomDeck.cards()
        val uniqueCard = cards.toSet()
        assertEquals(52, uniqueCard.size)
    }

    @Test
    fun `카드를_한장_뽑으면_덱_사이즈가_1_줄어든다`() {
        val randomDeck = RandomDeck()
        val card = randomDeck.draw()
        assertEquals(51, randomDeck.size())
    }


    @Test
    fun `덱이_비어있으면_카드를_뽑을_수_없다`() {
        val randomDeck = RandomDeck()
        repeat(52) {
            randomDeck.draw()
        }
        val ex = assertThrows<IllegalStateException> { randomDeck.draw() }
        assertEquals("덱에 카드가 비었습니다.", ex.message)
    }

}