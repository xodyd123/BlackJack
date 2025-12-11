package com.taeyong.blackjack.view.mapper

import com.taeyong.blackjack.domain.dealear.Dealer
import com.taeyong.blackjack.domain.game.EndReason
import com.taeyong.blackjack.domain.game.GameResult
import com.taeyong.blackjack.domain.game.Winner
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
        val cardNumbers = dealer.cards.map { card ->
            card.rank.symbol
        }
        return ParticipantDto(dealer.score, cardNumbers)
    }

    fun initialDealerCard(dealer: Dealer): ParticipantDto {
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

    fun gameResult(gameResult: GameResult): String {
        return when (gameResult.endReason) {
            EndReason.NORMAL -> when(gameResult.winner) {
                Winner.PLAYER -> "${gameResult.winner.result}의 승리입니다!"
                Winner.DEALER -> "${gameResult.winner.result}의 승리입니다!"
                Winner.DRAW -> "${gameResult.winner.result}입니다."
            }

            EndReason.PLAYER_BUST -> "${gameResult.endReason.loser} Bust! ${gameResult.winner.result}의 승리입니다."
            EndReason.DEALER_BUST -> "${gameResult.endReason.loser} Bust! ${gameResult.winner.result}의 승리입니다."
        }
    }

}