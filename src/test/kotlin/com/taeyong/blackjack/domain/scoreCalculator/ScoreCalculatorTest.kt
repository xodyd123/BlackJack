package com.taeyong.blackjack.domain.scoreCalculator

import com.taeyong.blackjack.domain.card.Card
import com.taeyong.blackjack.domain.card.Rank
import com.taeyong.blackjack.domain.card.Suit
import com.taeyong.blackjack.domain.score.ScoreCalculator
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test
import kotlin.test.assertEquals

class ScoreCalculatorTest {

    @Test
    fun `숫자 카드 2는 2점으로 점수 계산한다`(){
        val calculator = ScoreCalculator()

        val totalScore = calculator.calculate(listOf(Card(Rank.TWO, Suit.CLUB)))

        assertEquals(2 , totalScore)
    }

    @Test
    fun `J , Q , K 는 10점으로 점수 계산한다`(){
        val calculator = ScoreCalculator()

        listOf(Card(Rank.J, Suit.CLUB), Card(Rank.K, Suit.CLUB), Card(Rank.Q, Suit.CLUB)).forEach { card ->
            val score = calculator.calculate(listOf(card))
            assertEquals(10 , score)
        }
    }

    @Test
    fun `A는_21을_넘기지_않기_위해_1점으로_계산된다`(){

        val calculator = ScoreCalculator()

        val totalScore = calculator.calculate(
            listOf(Card(Rank.A, Suit.CLUB), Card(Rank.J, Suit.CLUB), Card(Rank.TEN, Suit.CLUB))
        )

        assertEquals(21 , totalScore)

    }

    @Test
    fun `A는_21을_넘기지_않으면_11점으로_계산된다`() {
        val calculator = ScoreCalculator()

        val totalScore = calculator.calculate(
            listOf(Card(Rank.A, Suit.CLUB), Card(Rank.FOUR, Suit.CLUB), Card(Rank.SIX, Suit.CLUB))
        )

        assertEquals(21, totalScore)
    }

    @Test
    fun `여러_숫자_카드의_합을_계산한다`(){
        val calculator = ScoreCalculator()

        val totalScore = calculator.calculate(
            listOf(Card(Rank.TWO, Suit.CLUB), Card(Rank.THREE, Suit.SPADE), Card(Rank.FOUR, Suit.SPADE))
        )

        assertEquals(9, totalScore)
    }

    @Test
    fun `A가_두장인_경우_하나는_11하나는_1로_계산된다`() {
        val calculator = ScoreCalculator()

        val totalScore = calculator.calculate(
            listOf(Card(Rank.A, Suit.CLUB), Card(Rank.A, Suit.CLUB))   // 11 + 1 = 12
        )

        assertEquals(12, totalScore)
    }

    @Test
    fun `A가_두장이고_숫자카드가_있는_경우_21을_넘지_않도록_계산된다`() {
        val calculator = ScoreCalculator()

        val totalScore = calculator.calculate(
            listOf(Card(Rank.A, Suit.CLUB), Card(Rank.A, Suit.CLUB), Card(Rank.NINE, Suit.CLUB))
        )

        assertEquals(21, totalScore)
    }

    @Test
    fun `A가_없는_경우에는_단순_합으로_계산된다`() {
        val calculator = ScoreCalculator()

        val totalScore = calculator.calculate(
            listOf(Card(Rank.TEN, Suit.CLUB), Card(Rank.NINE, Suit.CLUB), Card(Rank.THREE, Suit.CLUB)) // 22
        )

        assertEquals(22, totalScore)
    }

    @Test
    fun `A가_두장이고_10이_있으면_12점으로_계산된다`() {
        val calculator = ScoreCalculator()

        val totalScore = calculator.calculate(
            listOf(Card(Rank.A, Suit.CLUB), Card(Rank.A, Suit.CLUB), Card(Rank.TEN, Suit.CLUB))
        )

        assertEquals(12, totalScore)
    }


}