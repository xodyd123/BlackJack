package com.taeyong.blackjack.domain.deck

import com.taeyong.blackjack.domain.card.Card

interface Deck {


    fun draw(): Card

    fun size(): Int
}