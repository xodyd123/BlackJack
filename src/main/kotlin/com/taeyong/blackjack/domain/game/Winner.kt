package com.taeyong.blackjack.domain.game

enum class Winner(val result: String) {
    PLAYER("플레이어"), DEALER("딜러"), DRAW("무승부")
}