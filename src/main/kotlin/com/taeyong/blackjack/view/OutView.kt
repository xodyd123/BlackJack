package com.taeyong.blackjack.view

import com.taeyong.blackjack.view.dto.ParticipantDto

interface OutView {

    fun startPrompt()

    fun playerCardResult(participantDto: ParticipantDto)

    fun dealerCardResult(participantDto: ParticipantDto)
}