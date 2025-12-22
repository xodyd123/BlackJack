package com.taeyong.blackjack.view

import com.taeyong.blackjack.domain.game.GameResult
import com.taeyong.blackjack.domain.snapshot.InitialSnapshot
import com.taeyong.blackjack.domain.snapshot.ParticipantSnapshot

class FakeOutView(val promptMessages: MutableList<String>) : OutView {

    override fun startPrompt() {
        promptMessages.add("블랙잭 게임을 시작합니다.")
    }

    override fun receiveCardPrompt() {
        TODO("Not yet implemented")
    }

    override fun playerHitCardPrompt() {
        TODO("Not yet implemented")
    }

    override fun playerEndPrompt() {
        TODO("Not yet implemented")
    }

    override fun playerBustPrompt() {
        TODO("Not yet implemented")
    }

    override fun printInitialRound(snapshot: InitialSnapshot) {
        TODO("Not yet implemented")
    }


    override fun showGameResult(result: GameResult) {
        TODO("Not yet implemented")
    }

    override fun printPlayerRound(snapshot: ParticipantSnapshot) {
        TODO("Not yet implemented")
    }

    override fun dealerTurnStartPrompt() {
        TODO("Not yet implemented")
    }

    override fun dealerHitCardPrompt() {
        TODO("Not yet implemented")
    }

    override fun restartGameDecisionPrompt() {
        TODO("Not yet implemented")
    }

    override fun printDealerRound(snapshot: ParticipantSnapshot) {
        TODO("Not yet implemented")
    }

    override fun dealerTurnResult(snapshots: List<ParticipantSnapshot>, result: GameResult) {
        TODO("Not yet implemented")
    }

}