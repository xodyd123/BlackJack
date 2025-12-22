package com.taeyong.blackjack.service

import com.taeyong.blackjack.domain.game.GameResult
import com.taeyong.blackjack.domain.snapshot.ParticipantSnapshot

sealed interface StepResult {
    data class PlayerUpdated(val player: ParticipantSnapshot) : StepResult
    data class DealerUpdated(val dealer: List<ParticipantSnapshot>, val result: GameResult) : StepResult
    data class RoundEnded(val player: ParticipantSnapshot) : StepResult
}