package com.taeyong.blackjack.domain.player

import com.taeyong.blackjack.error.ErrorMessage
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayerDecisionTest {

    private fun assertInvalid(input: String) {
        val ex = assertThrows<IllegalArgumentException> { PlayerDecision.fromInput(input) }
        assertEquals(ErrorMessage.INVALID_PLAYER_DECISION, ex.message)
    }

    @Test
    fun `인풋_값이_숫자라면_예외가_발생한다`(){
        assertInvalid("1")
    }

    @Test
    fun `인풋_값이_y,n이_아닌_알파벳이_들어오면_예외가_발생한다`(){
        assertInvalid("t")
    }

    @Test
    fun `인풋_값이_빈_문자열이면_예외가_발생한다`() {
        assertInvalid("")
    }

    @Test
    fun `인풋_값이_공백만_있으면_예외가_발생한다`() {
        assertInvalid(" ")
    }

    @Test
    fun `인풋_값이_두_글자_이상이면_예외가_발생한다`() {
        assertInvalid("1t")
    }

    @Test
    fun `인풋_값이_소문자_y이_들어오면_HIT을_반환한다`() {
        val status = PlayerDecision.fromInput("y")
        assertEquals(PlayerDecision.HIT, status)
    }

    @Test
    fun `인풋_값이_소문자_n이_들어오면_STAND를_반환한다`() {
        val status = PlayerDecision.fromInput("n")
        assertEquals(PlayerDecision.STAND, status)
    }

    @Test
    fun `인풋_값이_대문자_Y이_들어오면_HIT을_반환한다`() {
        val status = PlayerDecision.fromInput("Y")
        assertEquals(PlayerDecision.HIT, status)
    }

    @Test
    fun `인풋_값이_대문자_N이_들어오면_STAND를_반환한다`() {
        val status = PlayerDecision.fromInput("N")
        assertEquals(PlayerDecision.STAND, status)
    }


    @Test
    fun `인풋_값이_공백을_포함한_소문자_y_n은_HIT과_STAND를_반환한다`() {
        val yStatus = PlayerDecision.fromInput(" y")
        val nStatus = PlayerDecision.fromInput("n ")
        assertEquals(PlayerDecision.HIT, yStatus)
        assertEquals(PlayerDecision.STAND, nStatus)
    }

    @Test
    fun `인풋_값이_공백을_포함한_대문자_Y_N은_HIT과_STAND를_반환한다`() {
        val yStatus = PlayerDecision.fromInput(" Y")
        val nStatus = PlayerDecision.fromInput("N ")
        assertEquals(PlayerDecision.HIT, yStatus)
        assertEquals(PlayerDecision.STAND, nStatus)
    }
}