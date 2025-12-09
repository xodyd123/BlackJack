package com.taeyong.blackjack.view.dto

import com.taeyong.blackjack.domain.player.Player
import com.taeyong.blackjack.domain.score.ScoreCalculator

data class PlayerDto(
    val sum: Int,
    val cardNumbers: List<Int>
) {

}