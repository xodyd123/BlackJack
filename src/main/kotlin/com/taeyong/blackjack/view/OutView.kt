package com.taeyong.blackjack.view

import com.taeyong.blackjack.view.dto.PlayerDto

interface OutView {

    fun startPrompt()

    fun playerCardResult(playerDto: PlayerDto)
}