package com.taeyong.blackjack.domain.deck

class Deck(val cardTotalCount : Int) {

    private val deck = mutableMapOf<Int , String>()

    init {
        deck[0] = "ff"
    }

}