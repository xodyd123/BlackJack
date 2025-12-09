package com.taeyong.blackjack.view

import com.taeyong.blackjack.view.dto.ParticipantDto

class FakeOutView(val promptMessages: MutableList<String>) : OutView {

    override fun startPrompt() {
        promptMessages.add("블랙잭 게임을 시작합니다.")
    }

    override fun playerCardResult(participantDto: ParticipantDto) {
        val cards = participantDto.cardNumbers
        var result = "플레이어 카드: $cards - 현재점수: ${participantDto.sum}"
        promptMessages.add(result)
    }

    override fun dealerCardResult(participantDto: ParticipantDto) {
        val cards = participantDto.cardNumbers
        var result = "딜러 카드: $cards"
        promptMessages.add(result)
    }

}