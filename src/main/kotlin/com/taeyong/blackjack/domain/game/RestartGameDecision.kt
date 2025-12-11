package com.taeyong.blackjack.domain.game

import com.taeyong.blackjack.error.ErrorMessage

enum class RestartGameDecision {
    RESTART, END;

    companion object {
        fun fromInput(input: String): RestartGameDecision {
            return when (input.trim()) {
                "1" -> RestartGameDecision.RESTART
                "2" -> RestartGameDecision.END
                else -> throw IllegalArgumentException(ErrorMessage.INVALID_RESTART_GAME_DECISION)
            }
        }
    }

}