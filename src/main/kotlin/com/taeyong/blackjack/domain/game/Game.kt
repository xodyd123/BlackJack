package com.taeyong.blackjack.domain.game

import com.taeyong.blackjack.domain.dealear.Dealer
import com.taeyong.blackjack.domain.deck.Deck
import com.taeyong.blackjack.domain.player.Player

class Game(
    private val deck: Deck,
    private val player: Player,
    private val dealer: Dealer,
) {
    fun startRound() {

    }

    fun dealInitialCards() {
        repeat(2) {
            player.receive(deck.draw())
            dealer.receive(deck.draw())
        }
    }

    fun playDealerTurn() {
        dealer.playTurn(deck)
    }

    fun playPlayerTurn() {
        player.hit(deck)
    }

    fun judge(): GameResult {
        return when {
            player.isBust -> GameResult(Winner.DEALER, EndReason.PLAYER_BUST)
            dealer.isBust -> GameResult(Winner.PLAYER, EndReason.DEALER_BUST)
            player.score > dealer.score -> GameResult(Winner.PLAYER, EndReason.NORMAL)
            dealer.score > player.score -> GameResult(Winner.DEALER, EndReason.NORMAL)
            else -> GameResult(Winner.DRAW, EndReason.NORMAL)
        }
    }
}