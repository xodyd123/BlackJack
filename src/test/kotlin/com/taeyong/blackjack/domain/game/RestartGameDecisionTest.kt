package com.taeyong.blackjack.domain.game

import com.taeyong.blackjack.error.ErrorMessage
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class RestartGameDecisionTest {

    private fun assertInvalid(input: String) {
        val ex = assertThrows<IllegalArgumentException> { RestartGameDecision.fromInput(input) }
        assertEquals(ErrorMessage.INVALID_RESTART_GAME_DECISION, ex.message)
    }

    @Test
    fun `인풋값이_1이면_RESTART를_반환한다`() {
        val result = RestartGameDecision.fromInput("1")
        assertEquals(RestartGameDecision.RESTART, result)
    }

    @Test
    fun `인풋값이_2이면_END를_반환한다`() {
        val result = RestartGameDecision.fromInput("2")
        assertEquals(RestartGameDecision.END, result)
    }

    @Test
    fun `인풋값이_공백을_포함한_1이면_RESTART를_반환한다`() {
        val result = RestartGameDecision.fromInput(" 1")
        assertEquals(RestartGameDecision.RESTART, result)
    }

    @Test
    fun `인풋값이_공백을_포함한_2이면_END를_반환한다`() {
        val result = RestartGameDecision.fromInput("2 ")
        assertEquals(RestartGameDecision.END, result)
    }


    @Test
    fun `인풋값이_빈_문자열_이면_예외가_발생한다`() {
        assertInvalid("")
    }

    @Test
    fun `인풋값이_공백이면_예외가_발생한다`() {
        assertInvalid(" ")
    }

    @Test
    fun `인풋값이_1,2가_아닌_문자가_오면_예외가_발생한다`() {
        assertInvalid("t")
    }

    @Test
    fun `인풋값이_1,2가_아닌_숫자면_예외가_발생한다`() {
        assertInvalid("3")
    }

    @Test
    fun `인풋값이_여러_문자가_오면_예외가_발생한다`() {
        assertInvalid("12")
    }

}