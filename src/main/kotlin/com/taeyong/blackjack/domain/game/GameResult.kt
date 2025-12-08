package com.taeyong.blackjack.domain.game

data class GameResult(
    val winner: Winner,
    val endReason: EndReason
)
