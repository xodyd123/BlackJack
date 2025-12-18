package com.taeyong.blackjack.service

import com.taeyong.blackjack.domain.game.Game
import com.taeyong.blackjack.domain.snapshot.InitialSnapshot

class GameService(private val game: Game) {

    fun startRound(): InitialSnapshot {
        game.startRound()
        return game.initialRoundSnapshot()
    }
}