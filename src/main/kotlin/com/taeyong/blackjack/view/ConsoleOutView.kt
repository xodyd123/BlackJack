package com.taeyong.blackjack.view

import com.taeyong.blackjack.view.dto.ParticipantDto

object ConsoleOutView : OutView {

    const val START_PROMPT_MESSAGE = "블랙잭 게임을 시작합니다."

    const val CARD_RECEIVE_MESSAGE = "카드를 더 받으시겠습니까? (Y/N)"

    override fun startPrompt() {
        println(START_PROMPT_MESSAGE)
    }

    override fun receiveCardPrompt() {
        println(CARD_RECEIVE_MESSAGE)
    }

    override fun playerCardResult(participantDto: ParticipantDto) {
        val cards = participantDto.cardNumbers
        var result = "플레이어 카드: $cards - 현재점수: ${participantDto.sum}"
        println(result)
    }

    override fun dealerCardResult(participantDto: ParticipantDto) {
        val cards = participantDto.cardNumbers
        var result = "딜러 카드: $cards"
        println(result)
    }


}