package com.taeyong.blackjack.controller

import com.taeyong.blackjack.domain.dealear.Dealer
import com.taeyong.blackjack.domain.game.Game
import com.taeyong.blackjack.domain.player.Player
import com.taeyong.blackjack.view.InputView
import com.taeyong.blackjack.view.OutView
import com.taeyong.blackjack.view.mapper.ParticipantViewMapper


class GameController(
    private val outView: OutView,
    private val game: Game,
    private val participantViewMapper: ParticipantViewMapper,
    private val player: Player,
    private val dealer: Dealer,
    private val inputView: InputView
) {
    fun run() {
        outView.startPrompt()
        game.dealInitialCards(player, dealer)
        val currentPlayerResult = participantViewMapper.from(player)
        val currentDealerResult = participantViewMapper.from(dealer)
        outView.playerCardResult(currentPlayerResult)
        outView.dealerCardResult(currentDealerResult)
        outView.receiveCardPrompt()
        val inputs = inputView.readLine()
    }
}