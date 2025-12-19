package com.taeyong.blackjack.view

import com.taeyong.blackjack.domain.game.GameResult
import com.taeyong.blackjack.domain.snapshot.InitialSnapshot
import com.taeyong.blackjack.domain.snapshot.ParticipantSnapshot


interface OutView {

    fun startPrompt()

    fun receiveCardPrompt()

    fun playerHitCardPrompt()

    fun playerEndPrompt()

    fun playerBustPrompt()

    fun printInitialRound(snapshot: InitialSnapshot)

    fun showGameResult(result: GameResult)

    fun printPlayerRound(snapshot: ParticipantSnapshot)

    fun dealerTurnStartPrompt()

    fun dealerHitCardPrompt()

    fun restartGameDecisionPrompt()

    fun printDealerRound(snapshot: ParticipantSnapshot)

    fun dealerTurnResult(snapshots: List<ParticipantSnapshot>)
}