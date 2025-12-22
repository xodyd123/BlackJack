package controller

import com.taeyong.blackjack.controller.GameController
import com.taeyong.blackjack.domain.card.Card
import com.taeyong.blackjack.domain.card.Rank
import com.taeyong.blackjack.domain.card.Suit
import com.taeyong.blackjack.domain.dealear.Dealer
import com.taeyong.blackjack.domain.deck.FakeDeck
import com.taeyong.blackjack.domain.deck.RandomDeck
import com.taeyong.blackjack.domain.game.Game
import com.taeyong.blackjack.domain.player.Player
import com.taeyong.blackjack.domain.score.ScoreCalculator
import com.taeyong.blackjack.service.GameService
import org.junit.jupiter.api.Assertions.assertTrue
import com.taeyong.blackjack.view.FakeOutView
import com.taeyong.blackjack.view.InputView
import org.junit.jupiter.api.Test


class GameControllerTest {

    @Test
    fun `run을_호출하면_게임을_시작하고_시작_메시지를_출력한다`() {
        val promptMessages = mutableListOf<String>()
        val fakeOutView = FakeOutView(promptMessages)
        val scoreCalculator = ScoreCalculator()
        val player = Player(scoreCalculator)
        val dealer = Dealer(scoreCalculator)
        val deck = RandomDeck()
        val game = Game(deck, player, dealer)
        val controller = GameController(
            fakeOutView, GameService(game), InputView)
        controller.run()
        assertTrue { promptMessages.contains("블랙잭 게임을 시작합니다.")}
    }

    @Test
    fun `run을_호출하면_플레이어의_초기_카드를_뷰에_출력한다`(){
        val promptMessages = mutableListOf<String>()
        val fakeOutView = FakeOutView(promptMessages)
        val scoreCalculator = ScoreCalculator()
        val player = Player(scoreCalculator)
        val dealer = Dealer(scoreCalculator)
        val deck = FakeDeck(
            listOf(
                Card(Rank.Q, Suit.HEART),
                Card(Rank.K, Suit.SPADE),
                Card(Rank.J, Suit.SPADE),
                Card(Rank.Q, Suit.SPADE)
            )
        )
        val game = Game(deck, player, dealer)
        val controller = GameController(
            fakeOutView, GameService(game), InputView)
        controller.run()
        assertTrue(promptMessages.contains("플레이어 카드: [Q, J] - 현재점수: 20"))
    }

    @Test
    fun `run을_호출하면_딜러의_초기_카드를_뷰에_출력한다`(){
        val promptMessages = mutableListOf<String>()
        val fakeOutView = FakeOutView(promptMessages)
        val scoreCalculator = ScoreCalculator()
        val player = Player(scoreCalculator)
        val dealer = Dealer(scoreCalculator)
        val deck = FakeDeck(
            listOf(
                Card(Rank.Q, Suit.HEART),
                Card(Rank.K, Suit.SPADE),
                Card(Rank.J, Suit.SPADE),
                Card(Rank.Q, Suit.SPADE)
            )
        )
        val game = Game(deck, player, dealer)
        val controller = GameController(
            fakeOutView, GameService(game), InputView)
        controller.run()
        assertTrue(promptMessages.contains("딜러 카드: [K, ?]"))
    }

}