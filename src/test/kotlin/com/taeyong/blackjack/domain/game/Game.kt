package com.taeyong.blackjack.domain.game

import com.taeyong.blackjack.domain.deck.Deck
import com.taeyong.blackjack.domain.delear.Dealer
import com.taeyong.blackjack.domain.player.Player

class Game(
    private val player: Player,
    private val dealer: Dealer,
    private val deck: Deck
) {

    fun start() {
        for (i in 0 until 2) {
            player.receive(deck.draw())
            dealer.receive(deck.draw())
        }

    }
}