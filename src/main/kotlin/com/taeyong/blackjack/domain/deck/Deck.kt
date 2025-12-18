package com.taeyong.blackjack.domain.deck

import com.taeyong.blackjack.domain.card.Card

interface Deck {

    fun reset()


    fun draw(): Card

    fun size(): Int
}