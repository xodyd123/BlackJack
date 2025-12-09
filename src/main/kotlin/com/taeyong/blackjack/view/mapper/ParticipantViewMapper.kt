package com.taeyong.blackjack.view.mapper

import com.taeyong.blackjack.domain.dealear.Dealer
import com.taeyong.blackjack.domain.player.Player
import com.taeyong.blackjack.domain.score.ScoreCalculator
import com.taeyong.blackjack.view.dto.ParticipantDto

class ParticipantViewMapper(private val scoreCalculator: ScoreCalculator) {

    fun from(player: Player): ParticipantDto {
        val cardNumbers = player.cards.map { card ->
            card.rank.symbol
        }
        return ParticipantDto(player.score, cardNumbers)
    }

    fun from(dealer: Dealer): ParticipantDto {
        val lastIndex = dealer.cards.lastIndex
        val cardNumbers = dealer.cards.mapIndexed { index, card ->
            if (index == lastIndex) {
                "?"
            } else {
                card.rank.symbol
            }
        }
        return ParticipantDto(dealer.score, cardNumbers)
    }
}