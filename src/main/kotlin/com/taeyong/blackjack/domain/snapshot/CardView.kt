package com.taeyong.blackjack.domain.snapshot

import com.taeyong.blackjack.domain.card.Rank

sealed interface CardView {
    data class Face(val rank: Rank) : CardView
    data object Hidden : CardView
}