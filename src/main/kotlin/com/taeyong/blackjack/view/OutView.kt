package com.taeyong.blackjack.view

import com.taeyong.blackjack.domain.snapshot.InitialSnapshot


interface OutView {

    fun startPrompt()

    fun receiveCardPrompt()

    fun playerHitCardPrompt()

    fun playerEndPrompt()

    fun playerBustPrompt()

    fun printInitialRound(snapshot: InitialSnapshot)

    fun showGameResult(result : String)

    fun dealerTurnStartPrompt()

    fun dealerHitCardPrompt()

    fun restartGameDecisionPrompt()
}