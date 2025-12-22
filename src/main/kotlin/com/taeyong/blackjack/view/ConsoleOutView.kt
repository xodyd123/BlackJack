package com.taeyong.blackjack.view

import com.taeyong.blackjack.domain.game.EndReason
import com.taeyong.blackjack.domain.game.GameResult
import com.taeyong.blackjack.domain.game.Winner
import com.taeyong.blackjack.domain.snapshot.CardView
import com.taeyong.blackjack.domain.snapshot.InitialSnapshot
import com.taeyong.blackjack.domain.snapshot.ParticipantSnapshot

object ConsoleOutView : OutView {

    const val START_PROMPT_MESSAGE = "블랙잭 게임을 시작합니다."

    const val PLAYER_HIT_PROMPT = "카드를 더 받으시겠습니까? (Y/N)"

    const val PLAYER_HIT_NOTICE = "플레이어가 카드를 한 장 더 받았습니다."

    const val PLAYER_END_NOTICE = "플레이어의 턴을 종료합니다."

    const val PLAYER_BUST_NOTICE = "플레이어 Bust! 딜러의 승리입니다."

    const val DEALER_START_NOTICE = "딜러의 턴을 시작합니다."

    const val DEALER_HIT_NOTICE = "딜러가 카드를 한 장 더 받습니다."

    const val RESTART_GAME_DECISION = "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요."


    override fun startPrompt() {
        println(START_PROMPT_MESSAGE)
    }

    override fun receiveCardPrompt() {
        println(PLAYER_HIT_PROMPT)
    }

    override fun playerHitCardPrompt() {
        println(PLAYER_HIT_NOTICE)
    }

    override fun playerEndPrompt() {
        println(PLAYER_END_NOTICE )
    }

    override fun playerBustPrompt() {
        println(PLAYER_BUST_NOTICE)
    }

    override fun printInitialRound(snapshot: InitialSnapshot) {
        val player = snapshot.player

        printPlayerRound(player)

        val dealer = snapshot.dealer

        val dealerCardsText = dealer.cards.joinToString(prefix = "[", postfix = "]") { cardView ->
            when (cardView){
                is CardView.Face -> cardView.rank.symbol
                CardView.Hidden -> "?"
            }
        }

        val dealerScoreText = dealer.score?.toString() ?: "?"
        println("딜러 카드: $dealerCardsText - 현재 점수: $dealerScoreText")
    }

    override fun printPlayerRound(player: ParticipantSnapshot) {
        playerHitCardPrompt()

        val playerCardsText = player.cards.joinToString(prefix = "[", postfix = "]") { cardView ->
            when (cardView) {
                is CardView.Face -> cardView.rank.symbol
                CardView.Hidden -> "?"
            }
        }
        val playerScoreText = player.score?.toString() ?: "?"
        println("플레이어 카드: $playerCardsText - 현재 점수: $playerScoreText")
        if(player.isBust) {
            playerBustPrompt()
        }
    }


    override fun showGameResult(gameResult: GameResult) {
        when (gameResult.endReason) {
            EndReason.NORMAL -> when(gameResult.winner) {
                Winner.PLAYER -> println("${gameResult.winner.result}의 승리입니다.")
                Winner.DEALER -> println("${gameResult.winner.result}의 승리입니다.")
                Winner.DRAW -> println("${gameResult.winner.result}입니다.")
            }

            EndReason.PLAYER_BUST -> println("${gameResult.endReason.loser} Bust! ${gameResult.winner.result}의 승리입니다.")
            EndReason.DEALER_BUST -> println("${gameResult.endReason.loser} Bust! ${gameResult.winner.result}의 승리입니다.")
        }
    }

    override fun dealerTurnStartPrompt() {
        println(DEALER_START_NOTICE)
    }

    override fun dealerHitCardPrompt() {
        println(DEALER_HIT_NOTICE)
    }

    override fun restartGameDecisionPrompt() {
        println(RESTART_GAME_DECISION)
    }

    override fun printDealerRound(snapshot: ParticipantSnapshot) {
        val playerCardsText = snapshot.cards.joinToString(prefix = "[", postfix = "]") { cardView ->
            when (cardView) {
                is CardView.Face -> cardView.rank.symbol
                CardView.Hidden -> "?"
            }
        }
        val playerScoreText = snapshot.score?.toString() ?: "?"
        dealerHitCardPrompt()
        println("딜러 카드: $playerCardsText - 현재 점수: $playerScoreText")
    }

    override fun dealerTurnResult(snapshots: List<ParticipantSnapshot>, result: GameResult) {
        dealerTurnStartPrompt()

        snapshots.forEach{ snapshot ->
            printDealerRound(snapshot)

        }

        showGameResult(result)

    }

}