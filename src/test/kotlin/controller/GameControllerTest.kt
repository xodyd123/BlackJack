package controller

import com.taeyong.blackjack.domain.dealear.Dealer
import com.taeyong.blackjack.domain.deck.FakeDeck
import com.taeyong.blackjack.domain.deck.RandomDeck
import com.taeyong.blackjack.domain.game.Game
import com.taeyong.blackjack.domain.hand.Hand
import com.taeyong.blackjack.domain.player.Player
import com.taeyong.blackjack.domain.score.ScoreCalculator
import view.FakeOutView
import kotlin.test.Test
import kotlin.test.assertEquals


class GameControllerTest {

    @Test
    fun `run을_호출하면_게임을_시작하고_시작_메시지를_출력한다`() {
        val promptMessages = mutableListOf<String>()
        val fakeOutView = FakeOutView(promptMessages)
        val controller = GameController(fakeOutView)
        controller.run()
        assertEquals(listOf("블랙잭 게임을 시작합니다."), promptMessages)
    }

//    @Test
//    fun `run을_호출하면_플레이어와_딜러의_초기_카드를_뷰에_출력한다`(){
//        val promptMessages = mutableListOf<String>()
//        val fakeOutView = FakeOutView(promptMessages)
//        val scoreCalculator = ScoreCalculator()
//        val player = Player(Hand(scoreCalculator))
//        val dealer = Dealer(Hand(scoreCalculator))
//        val deck = RandomDeck()
//        val game = Game(player, dealer, deck)
//        val controller = GameController(fakeOutView, game)
//        controller.run()
//        assertEquals(listOf("블랙잭 게임을 시작합니다."), promptMessages)
//    }

}