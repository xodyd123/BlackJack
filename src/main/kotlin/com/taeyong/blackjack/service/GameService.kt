package com.taeyong.blackjack.service

import com.taeyong.blackjack.domain.game.Game
import com.taeyong.blackjack.domain.game.GameResult
import com.taeyong.blackjack.domain.snapshot.InitialSnapshot
import com.taeyong.blackjack.domain.snapshot.ParticipantSnapshot

class GameService(private val game: Game) {

    fun startRound(): InitialSnapshot {
        game.startRound()
        return game.initialRoundSnapshot()
    }

    fun playingPlayerTurn(): ParticipantSnapshot {
        return game.playPlayerTurn()
    }

    fun playingDealerTurn() : List<ParticipantSnapshot> {
        val participantSnapshots = mutableListOf<ParticipantSnapshot>()
        return game.playDealerTurn(participantSnapshots)
    }

    fun judgeWinner(): GameResult {
        return game.judge()
    }

}