package com.taeyong.blackjack.view

import com.taeyong.blackjack.view.dto.ParticipantDto

object ConsoleOutView : OutView {

    const val START_PROMPT_MESSAGE = "블랙잭 게임을 시작합니다."

    const val PLAYER_HIT_PROMPT = "카드를 더 받으시겠습니까? (Y/N)"

    const val PLAYER_HIT_NOTICE = "플레이어가 카드를 한 장 더 받았습니다."

    const val PLAYER_END_NOTICE = "플레이어의 턴을 종료합니다."

    const val PLAYER_BUST_NOTICE = "플레이어 Bust! 딜러의 승리입니다."

    override fun startPrompt() {
        println(START_PROMPT_MESSAGE)
    }

    override fun receiveCardPrompt() {
        println(PLAYER_HIT_PROMPT)
    }

    override fun hitCardPrompt() {
        println(PLAYER_HIT_NOTICE)
    }

    override fun playerEndPrompt() {
        println(PLAYER_END_NOTICE )
    }

    override fun playerBustPrompt() {
        println(PLAYER_BUST_NOTICE)
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