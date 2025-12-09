package com.taeyong.blackjack.domain.game

import com.taeyong.blackjack.domain.deck.Deck
import com.taeyong.blackjack.domain.dealear.Dealer
import com.taeyong.blackjack.domain.player.Player
import com.taeyong.blackjack.domain.player.PlayerDto

class Game(
    private val player: Player,
    private val dealer: Dealer,
    private val deck: Deck
) {

    fun start(): PlayerDto {
        repeat(2) {
            player.receive(deck.draw())
            dealer.receive(deck.draw())
        }
        return player.cardResultDto()
    }

    fun playDealerTurn() {
        dealer.playTurn(deck)
    }

    fun playPlayerTurn() {
        player.hit(deck)
    }

    fun gameResult(): GameResult {
        return when {
            player.isBust -> GameResult(Winner.DEALER, EndReason.PLAYER_BUST)
            dealer.isBust -> GameResult(Winner.PLAYER, EndReason.DEALER_BUST)
            player.score > dealer.score -> GameResult(Winner.PLAYER, EndReason.NORMAL)
            dealer.score > player.score -> GameResult(Winner.DEALER, EndReason.NORMAL)
            else -> GameResult(Winner.DRAW, EndReason.NORMAL)
        }
    }
}