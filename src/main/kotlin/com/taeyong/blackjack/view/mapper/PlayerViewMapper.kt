package com.taeyong.blackjack.view.mapper

import com.taeyong.blackjack.domain.player.Player
import com.taeyong.blackjack.domain.score.ScoreCalculator
import com.taeyong.blackjack.view.dto.PlayerDto

class PlayerViewMapper(private val scoreCalculator: ScoreCalculator) {
    fun from(player: Player): PlayerDto {
        val cardNumbers = player.cards.map { card ->
            card.rank.baseScoreIgnoringAce
        }
        return PlayerDto(player.score, cardNumbers)
    }
}