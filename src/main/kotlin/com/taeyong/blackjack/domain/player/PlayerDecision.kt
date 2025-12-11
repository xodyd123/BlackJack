package com.taeyong.blackjack.domain.player

import com.taeyong.blackjack.error.ErrorMessage

enum class PlayerDecision {
    HIT, STAND;

    companion object {
        fun fromInput(input: String): PlayerDecision {
            return when (input.trim().lowercase()) {
                "y" -> HIT
                "n" -> STAND
                else -> throw IllegalArgumentException(ErrorMessage.INVALID_PLAYER_DECISION)
            }
        }
    }
}