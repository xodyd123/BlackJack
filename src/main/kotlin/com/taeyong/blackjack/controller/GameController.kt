package com.taeyong.blackjack.controller

import com.taeyong.blackjack.domain.dealear.Dealer
import com.taeyong.blackjack.domain.game.Game
import com.taeyong.blackjack.domain.player.Player
import com.taeyong.blackjack.view.OutView
import com.taeyong.blackjack.view.mapper.PlayerViewMapper

class GameController(
    private val outView: OutView,
    private val game: Game,
    private val playerViewMapper: PlayerViewMapper,
    private val player: Player,
    private val dealer: Dealer
) {
    fun run() {
        outView.startPrompt()
        game.dealInitialCards(player, dealer)
        val currentResult = playerViewMapper.from(player)
        outView.playerCardResult(currentResult)
    }
}