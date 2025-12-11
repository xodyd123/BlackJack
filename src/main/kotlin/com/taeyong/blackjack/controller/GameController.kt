package com.taeyong.blackjack.controller

import com.taeyong.blackjack.domain.dealear.Dealer
import com.taeyong.blackjack.domain.dealear.Dealer.Companion.DEALER_STAND_SCORE
import com.taeyong.blackjack.domain.game.Game
import com.taeyong.blackjack.domain.player.Player
import com.taeyong.blackjack.domain.player.PlayerDecision
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
        val currentDealerResult = participantViewMapper.initialDealerCard(dealer)
        outView.playerCardResult(currentPlayerResult)
        outView.dealerInitialCardResult(currentDealerResult)
        playerTurn()
        if (!player.isBust) {
            dealerTurn()
            gameResult()
        }

    }

    private fun gameResult() {
        val result = game.judge(player, dealer)
        val gameResult = participantViewMapper.gameResult(result)
        outView.showGameResult(gameResult)
    }

    private fun dealerTurn() {
        outView.dealerTurnStartPrompt()
        while (dealer.score < DEALER_STAND_SCORE) {
            game.playDealerTurn(dealer)
            val currentDealerResult = participantViewMapper.from(dealer)
            outView.dealerHitCardPrompt()
            outView.dealerCardResult(currentDealerResult)

        }
    }

    private fun playerTurn() {
        var playerTurn = true
        fun receiveCard() {
            outView.playerHitCardPrompt()
            game.playPlayerTurn(player)
            val currentPlayerResult = participantViewMapper.from(player)
            outView.playerCardResult(currentPlayerResult)
            if (player.isBust) {
                playerTurn = false
            }
        }
        while (true) {
            outView.receiveCardPrompt()
            val input = inputView.readLine()
            val playerDecision = PlayerDecision.fromInput(input)

            when (playerDecision) {
                PlayerDecision.HIT -> receiveCard()
                PlayerDecision.STAND -> {
                    outView.playerEndPrompt()
                    playerTurn = false
                }
            }
            if (!playerTurn) {
                if (player.isBust){
                    outView.playerBustPrompt()
                }
                break
            }

        }
    }


}