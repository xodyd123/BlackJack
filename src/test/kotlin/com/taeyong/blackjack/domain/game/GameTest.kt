package com.taeyong.blackjack.domain.game

import com.taeyong.blackjack.domain.card.Card
import com.taeyong.blackjack.domain.card.Rank
import com.taeyong.blackjack.domain.card.Suit
import com.taeyong.blackjack.domain.deck.FakeDeck
import com.taeyong.blackjack.domain.deck.RandomDeck
import com.taeyong.blackjack.domain.dealear.Dealer
import com.taeyong.blackjack.domain.player.Player
import com.taeyong.blackjack.domain.score.ScoreCalculator
import com.taeyong.blackjack.service.GameService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test


class GameTest {

    @Test
    fun `게임이_시작되면_플레이어와_딜러는_카드_2장을_받는다`() {
        val scoreCalculator = ScoreCalculator()
        val player = Player(scoreCalculator)
        val dealer = Dealer(scoreCalculator)
        val game = Game(RandomDeck(), player, dealer)
        val gameService = GameService(game)
        gameService.startRound()

        assertEquals(2, dealer.size)
        assertEquals(2, player.size)
    }

    @Test
    fun `딜러_턴에서는_규칙에_따라_카드를_받고_멈춘다`(){
        val scoreCalculator = ScoreCalculator()
        val player = Player(scoreCalculator)
        val dealer = Dealer(scoreCalculator)
        val deck = FakeDeck(listOf(Card(Rank.K, Suit.SPADE), Card(Rank.J, Suit.HEART)))
        val game = Game(deck, player, dealer)
        game.playDealerTurn(mutableListOf())

        assertEquals(2, dealer.size)
        assertEquals(20, dealer.score)
        assertEquals(2, deck.drawCount)
        assertFalse { dealer.shouldHit }
        assertFalse { dealer.isBust }
    }

    @Test
    fun `플레이어_턴에서는_규칙에_따라_카드를_받고_멈춘다`() {
        val scoreCalculator = ScoreCalculator()
        val player = Player(scoreCalculator)
        val dealer = Dealer(scoreCalculator)
        val deck = FakeDeck(listOf(Card(Rank.K, Suit.SPADE), Card(Rank.J, Suit.HEART)))
        val game = Game(deck, player, dealer)
        game.playPlayerTurn()

        assertEquals(1, player.size)
        assertEquals(10, player.score)
        assertEquals(1, deck.drawCount)
        assertFalse { player.isBust }
    }

    @Test
    fun `플레이어가_BUST_상태이면_딜러의_승리이다`() {
        val scoreCalculator = ScoreCalculator()
        val player = Player(scoreCalculator)
        val dealer = Dealer(scoreCalculator)
        val deck = FakeDeck(listOf(Card(Rank.K, Suit.SPADE), Card(Rank.J, Suit.HEART), Card(Rank.TWO , Suit.SPADE)))
        val game = Game(deck, player, dealer)
        game.playPlayerTurn()
        game.playPlayerTurn()
        game.playPlayerTurn()
        val result = game.judge()

        assertTrue { player.isBust }
        assertEquals(3, player.size)
        assertEquals(22, player.score)
        assertEquals(3, deck.drawCount)
        assertEquals(Winner.DEALER, result.winner)
        assertEquals(EndReason.PLAYER_BUST, result.endReason)

    }

    @Test
    fun `딜러가_BUST_상태이면_플레이어의_승리이다`() {
        val scoreCalculator = ScoreCalculator()
        val player = Player(scoreCalculator)
        val dealer = Dealer(scoreCalculator)
        val deck = FakeDeck(listOf(Card(Rank.K, Suit.SPADE), Card(Rank.SIX, Suit.HEART), Card(Rank.SIX, Suit.SPADE)))
        val game = Game(deck, player, dealer)

        game.playDealerTurn(mutableListOf())
        val result = game.judge()

        assertTrue { dealer.isBust }
        assertEquals(3, dealer.size)
        assertEquals(22, dealer.score)
        assertEquals(3, deck.drawCount)
        assertEquals(Winner.PLAYER, result.winner)
        assertEquals(EndReason.DEALER_BUST, result.endReason)

    }

    @Test
    fun `플레이어가_딜러보다_점수가_높으면_플레이어의_승리다`() {
        val scoreCalculator = ScoreCalculator()
        val player = Player(scoreCalculator)
        val dealer = Dealer(scoreCalculator)
        val deck = FakeDeck(
            listOf(
                Card(Rank.K, Suit.SPADE),
                Card(Rank.K, Suit.HEART),
                Card(Rank.J, Suit.HEART),
                Card(Rank.SIX, Suit.HEART),
                Card(Rank.THREE, Suit.SPADE)
            )
        )
        val game = Game(deck, player, dealer)

        game.playPlayerTurn()
        game.playPlayerTurn()
        game.playDealerTurn(mutableListOf())
        val result = game.judge()

        assertFalse { player.isBust }
        assertFalse { dealer.isBust }
        assertEquals(3, dealer.size)
        assertEquals(5, deck.drawCount)
        assertEquals(19, dealer.score)
        assertEquals(20, player.score)
        assertEquals(Winner.PLAYER, result.winner)
        assertEquals(EndReason.NORMAL, result.endReason)

    }

    @Test
    fun `딜러가_플레이어보다_점수가_높으면_딜러의_승리다`() {

        val scoreCalculator = ScoreCalculator()
        val player = Player(scoreCalculator)
        val dealer = Dealer(scoreCalculator)
        val deck = FakeDeck(
            listOf(
                Card(Rank.K, Suit.SPADE),
                Card(Rank.NINE, Suit.HEART),
                Card(Rank.J, Suit.HEART),
                Card(Rank.SIX, Suit.HEART),
                Card(Rank.FOUR, Suit.SPADE)
            )
        )
        val game = Game(deck, player, dealer)

        game.playPlayerTurn()
        game.playPlayerTurn()
        game.playDealerTurn(mutableListOf())
        val result = game.judge()

        assertFalse { player.isBust }
        assertFalse { dealer.isBust }
        assertEquals(3, dealer.size)
        assertEquals(5, deck.drawCount)
        assertEquals(20, dealer.score)
        assertEquals(19, player.score)
        assertEquals(Winner.DEALER, result.winner)
        assertEquals(EndReason.NORMAL, result.endReason)

    }

    @Test
    fun `플레이어와_딜러가_점수가_같으면_무승부이다`() {

        val scoreCalculator = ScoreCalculator()
        val player = Player(scoreCalculator)
        val dealer = Dealer(scoreCalculator)
        val deck = FakeDeck(
            listOf(
                Card(Rank.K, Suit.SPADE),
                Card(Rank.NINE, Suit.HEART),
                Card(Rank.J, Suit.HEART),
                Card(Rank.SIX, Suit.HEART),
                Card(Rank.THREE, Suit.SPADE)
            )
        )
        val game = Game(deck, player, dealer)

        game.playPlayerTurn()
        game.playPlayerTurn()
        game.playDealerTurn(mutableListOf())
        val result = game.judge()

        assertFalse { player.isBust }
        assertFalse { dealer.isBust }
        assertEquals(3, dealer.size)
        assertEquals(5, deck.drawCount)
        assertEquals(19, dealer.score)
        assertEquals(19, player.score)
        assertEquals(Winner.DRAW, result.winner)
        assertEquals(EndReason.NORMAL, result.endReason)

    }


}