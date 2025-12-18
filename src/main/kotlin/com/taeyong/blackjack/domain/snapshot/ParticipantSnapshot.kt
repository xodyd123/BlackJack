package com.taeyong.blackjack.domain.snapshot

data class ParticipantSnapshot(
    val cards: List<CardView>,
    val score: Int?,
    val isBust: Boolean
)
