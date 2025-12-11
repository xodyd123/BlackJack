package com.taeyong.blackjack.domain.game

enum class EndReason(val loser: String) {
    NORMAL(""),
    PLAYER_BUST("플레이어"),
    DEALER_BUST("딜러")
}