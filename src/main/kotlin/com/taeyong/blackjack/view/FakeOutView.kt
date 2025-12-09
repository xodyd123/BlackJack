package com.taeyong.blackjack.view

import com.taeyong.blackjack.domain.player.PlayerDto

class FakeOutView(val promptMessages: MutableList<String>) : OutView {

    override fun startPrompt() {
        promptMessages.add("블랙잭 게임을 시작합니다.")
    }

    override fun playerCardResult(playerDto: PlayerDto) {
        val cards = playerDto.cardNumbers
        var result = "플레이어 카드: $cards - 현재점수: ${playerDto.sum}"
        promptMessages.add(result)
    }

}