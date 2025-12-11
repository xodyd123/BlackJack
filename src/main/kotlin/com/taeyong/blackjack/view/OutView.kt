package com.taeyong.blackjack.view

import com.taeyong.blackjack.view.dto.ParticipantDto

interface OutView {

    fun startPrompt()

    fun receiveCardPrompt()

    fun playerHitCardPrompt()

    fun playerEndPrompt()

    fun playerBustPrompt()

    fun playerCardResult(participantDto: ParticipantDto)

    fun dealerInitialCardResult(participantDto: ParticipantDto)

    fun dealerCardResult(participantDto: ParticipantDto)

    fun showGameResult(result : String)

    fun dealerTurnStartPrompt()

    fun dealerHitCardPrompt()

    fun restartGameDecisionPrompt()
}