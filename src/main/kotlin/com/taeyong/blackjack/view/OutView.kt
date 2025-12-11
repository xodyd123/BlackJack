package com.taeyong.blackjack.view

import com.taeyong.blackjack.view.dto.ParticipantDto

interface OutView {

    fun startPrompt()

    fun receiveCardPrompt()

    fun hitCardPrompt()

    fun playerEndPrompt()

    fun playerBustPrompt()

    fun playerCardResult(participantDto: ParticipantDto)

    fun dealerCardResult(participantDto: ParticipantDto)
}