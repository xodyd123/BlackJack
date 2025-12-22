package com.taeyong.blackjack.domain.snapshot

data class InitialSnapshot(
    val player: ParticipantSnapshot,
    val dealer: ParticipantSnapshot

)
