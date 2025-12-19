package com.taeyong.blackjack.domain.game

import com.taeyong.blackjack.domain.dealear.Dealer
import com.taeyong.blackjack.domain.deck.Deck
import com.taeyong.blackjack.domain.player.Player
import com.taeyong.blackjack.domain.snapshot.InitialSnapshot
import com.taeyong.blackjack.domain.snapshot.ParticipantSnapshot

class Game(
    private val deck: Deck,
    private val player: Player,
    private val dealer: Dealer,
) {
    fun startRound() {
        player.resetHand()
        dealer.resetHand()
        deck.reset()
        dealInitialCards()
    }

    fun initialRoundSnapshot(): InitialSnapshot {
        val playerSnapShot = player.snapShot()
        val dealerSnapShot = dealer.initialSnapshotHiddenScore()
        return InitialSnapshot(playerSnapShot,dealerSnapShot)
    }

    fun playerRoundSnapshot(): ParticipantSnapshot {
        val playerSnapShot = player.snapShot()
        return playerSnapShot
    }

    private fun dealInitialCards() {
        repeat(2) {
            player.receive(deck.draw())
            dealer.initialReceive(deck.draw())
        }
    }

    fun playDealerTurn(snapshots: MutableList<ParticipantSnapshot>): List<ParticipantSnapshot> {
        return dealer.playTurn(deck, snapshots)
    }

    fun playPlayerTurn(): ParticipantSnapshot {
        player.hit(deck)
        return playerRoundSnapshot()
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