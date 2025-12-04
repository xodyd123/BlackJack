package com.taeyong.blackjack.domain.scoreCalculator

import com.taeyong.blackjack.domain.card.Card
import com.taeyong.blackjack.domain.card.Rank
import com.taeyong.blackjack.domain.score.ScoreCalculator
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test
import kotlin.test.assertEquals

class ScoreCalculatorTest {

    @Test
    fun `숫자 카드 2는 2점으로 점수 계산한다`(){
        val calculator = ScoreCalculator()

        val totalScore = calculator.calculate(listOf(Card(Rank.TWO)))

        assertEquals(2 , totalScore)
    }

    @Test
    fun `J , Q , K 는 10점으로 점수 계산한다`(){
        val calculator = ScoreCalculator()

        listOf(Card(Rank.J) , Card(Rank.K) , Card(Rank.Q)).forEach { card ->
            val score = calculator.calculate(listOf(card))
            assertEquals(10 , score)
        }
    }

    @Test
    fun `A는_21을_넘기지_않기_위해_1점으로_계산된다`(){

        val calculator = ScoreCalculator()

        val totalScore = calculator.calculate(
            listOf(Card(Rank.A), Card(Rank.J), Card(Rank.TEN))
        )

        assertEquals(21 , totalScore)

    }

    @Test
    fun `A는_21을_넘기지_않으면_11점으로_계산된다`() {
        val calculator = ScoreCalculator()

        val totalScore = calculator.calculate(
            listOf(Card(Rank.A), Card(Rank.FOUR), Card(Rank.SIX))
        )

        assertEquals(21, totalScore)
    }

    @Test
    fun `여러_숫자_카드의_합을_계산한다`(){
        val calculator = ScoreCalculator()

        val totalScore = calculator.calculate(
            listOf(Card(Rank.TWO), Card(Rank.THREE), Card(Rank.FOUR))
        )

        assertEquals(9, totalScore)
    }

    @Test
    fun `A가_두장인_경우_하나는_11하나는_1로_계산된다`() {
        val calculator = ScoreCalculator()

        val totalScore = calculator.calculate(
            listOf(Card(Rank.A), Card(Rank.A))   // 11 + 1 = 12
        )

        assertEquals(12, totalScore)
    }

    @Test
    fun `A가_두장이고_숫자카드가_있는_경우_21을_넘지_않도록_계산된다`() {
        val calculator = ScoreCalculator()

        val totalScore = calculator.calculate(
            listOf(Card(Rank.A), Card(Rank.A), Card(Rank.NINE))
        )

        assertEquals(21, totalScore)
    }

    @Test
    fun `A가_없는_경우에는_단순_합으로_계산된다`() {
        val calculator = ScoreCalculator()

        val totalScore = calculator.calculate(
            listOf(Card(Rank.TEN), Card(Rank.NINE), Card(Rank.THREE)) // 22
        )

        assertEquals(22, totalScore)
    }

    @Test
    fun `A가_두장이고_10이_있으면_12점으로_계산된다`() {
        val calculator = ScoreCalculator()

        val totalScore = calculator.calculate(
            listOf(Card(Rank.A), Card(Rank.A), Card(Rank.TEN))
        )

        assertEquals(12, totalScore)
    }


}