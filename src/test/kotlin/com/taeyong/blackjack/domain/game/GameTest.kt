package com.taeyong.blackjack.domain.game

import com.taeyong.blackjack.domain.deck.RandomDeck
import com.taeyong.blackjack.domain.delear.Dealer
import com.taeyong.blackjack.domain.hand.Hand
import com.taeyong.blackjack.domain.player.Player
import com.taeyong.blackjack.domain.score.ScoreCalculator
import kotlin.test.Test
import kotlin.test.assertEquals

class GameTest {

    @Test
    fun `게임이_시작되면_플레이어와_딜러는_카드_2장을_받는다`() {
        val scoreCalculator = ScoreCalculator()
        val player = Player(Hand(scoreCalculator))
        val dealer = Dealer(Hand(scoreCalculator))
        val game = Game(player, dealer, RandomDeck())
        game.start()

        assertEquals(2, dealer.size)
        assertEquals(2, player.size)
    }

    @Test
    fun `플레이어가_STAND_상태_이면_딜러의_턴으로_넘어간다`(){

    }

    @Test
    fun `플레이어가_HIT_상태_이면_플레이어의_턴이_계속된다`() {

    }

    @Test
    fun `플레이어가_BUST_상태이면_딜러의_승리이다`() {

    }

    @Test
    fun `딜러가_BUST_상태이면_플레이어의_승리이다`() {

    }

    @Test
    fun `플레이어가_딜러보다_점수가_높으면_플레이어의_승리다`() {

    }

    @Test
    fun `딜러가_플레이어보다_점수가_높으면_딜러의_승리다`() {

    }

    @Test
    fun `플레이어와_딜러가_점수가_같으면_무승부이다`() {

    }




}