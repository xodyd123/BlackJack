package com.taeyong.blackjack.view

import com.taeyong.blackjack.domain.player.PlayerDto

interface OutView {

    fun startPrompt()

    fun playerCardResult(playerDto: PlayerDto)
}