package com.taeyong.blackjack.service

import com.taeyong.blackjack.domain.game.Game
import com.taeyong.blackjack.domain.game.GameResult
import com.taeyong.blackjack.domain.player.PlayerDecision
import com.taeyong.blackjack.domain.snapshot.InitialSnapshot
import com.taeyong.blackjack.domain.snapshot.ParticipantSnapshot

class GameService(private val game: Game) {

    fun startRound(): InitialSnapshot {
        game.startRound()
        return game.initialRoundSnapshot()
    }

    private fun playingPlayerTurn(): ParticipantSnapshot {
        return game.playPlayerTurn()
    }

    private fun playingDealerTurn(): List<ParticipantSnapshot> {
        val participantSnapshots = mutableListOf<ParticipantSnapshot>()
        return game.playDealerTurn(participantSnapshots)
    }

    private fun judgeWinner(): GameResult {
        return game.judge()
    }

    fun nextStep(input: String): StepResult {
        return when (PlayerDecision.fromInput(input)) {
            PlayerDecision.HIT -> {
                val player = playingPlayerTurn()
                if (player.isBust) return StepResult.RoundEnded(player)
                StepResult.PlayerUpdated(player)
            }

            PlayerDecision.STAND -> {
                val dealer = playingDealerTurn()
                StepResult.DealerUpdated(dealer, judgeWinner())
            }
        }
    }

}