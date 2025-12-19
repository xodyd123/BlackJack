import camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest
import camp.nextstep.edu.missionutils.test.NsTest
import com.taeyong.blackjack.controller.GameController
import com.taeyong.blackjack.domain.card.Card
import com.taeyong.blackjack.domain.card.Rank
import com.taeyong.blackjack.domain.card.Suit
import com.taeyong.blackjack.domain.dealear.Dealer
import com.taeyong.blackjack.domain.deck.Deck
import com.taeyong.blackjack.domain.deck.FakeDeck
import com.taeyong.blackjack.domain.game.Game
import com.taeyong.blackjack.domain.player.Player
import com.taeyong.blackjack.domain.score.ScoreCalculator
import com.taeyong.blackjack.service.GameService
import com.taeyong.blackjack.view.ConsoleOutView
import com.taeyong.blackjack.view.InputView
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class ApplicationTest : NsTest() {

    private lateinit var deck: Deck


    fun runApp(deck: Deck) {
        val calculator = ScoreCalculator()
        val player = Player(calculator)
        val dealer = Dealer(calculator)
        val game = Game(deck, player, dealer)
        val controller = GameController(ConsoleOutView, GameService(game), InputView)
        controller.run()
    }

    override fun runMain() {
        runApp(deck)
    }

    @Test()
    fun `플레이어가_BUST_상태이면_딜러의_승리다`() {
        deck = FakeDeck(
            listOf<Card>(
                Card(Rank.K, Suit.SPADE),
                Card(Rank.TWO, Suit.HEART),
                Card(Rank.Q, Suit.SPADE),
                Card(Rank.SIX, Suit.CLUB),
                Card(Rank.TWO, Suit.SPADE)
            ))
        assertSimpleTest {
            run("y", "2")
            assertThat(output()).contains(
                "블랙잭 게임을 시작합니다.",
                "플레이어 카드: [K, Q] - 현재 점수: 20",
                "딜러 카드: [2, ?] - 현재 점수: ?",
                "카드를 더 받으시겠습니까? (Y/N)",
                "플레이어가 카드를 한 장 더 받았습니다",
                "플레이어 카드: [K, Q, 2] - 현재 점수: 22",
                "플레이어 Bust! 딜러의 승리입니다.",
                "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요."
            )
        }
    }

    @Test()
    fun `딜러가_BUST_상태이면_플레이어의_승리다`() {
        deck = FakeDeck(
            listOf<Card>(
                Card(Rank.K, Suit.SPADE),
                Card(Rank.Q, Suit.HEART),
                Card(Rank.TWO, Suit.SPADE),
                Card(Rank.TWO, Suit.CLUB),
                Card(Rank.THREE, Suit.SPADE),
                Card(Rank.J, Suit.CLUB)
            )
        )
        assertSimpleTest {
            run("y", "n", "2")
            assertThat(output()).contains(
                "블랙잭 게임을 시작합니다.",
                "플레이어 카드: [K, 2] - 현재 점수: 12",
                "딜러 카드: [Q, ?] - 현재 점수: ?",
                "카드를 더 받으시겠습니까? (Y/N)",
                "플레이어가 카드를 한 장 더 받았습니다",
                "플레이어 카드: [K, 2, 3] - 현재 점수: 15",
                "카드를 더 받으시겠습니까? (Y/N)",
                "딜러의 턴을 시작합니다.",
                "딜러 카드: [Q, 2] - 현재 점수: 12",
                "딜러가 카드를 한 장 더 받습니다.",
                "딜러 카드: [Q, 2, J] - 현재 점수: 22",
                "딜러 Bust! 플레이어의 승리입니다.",
                "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요."
            )
        }
    }

    @Test()
    fun `플레이어가_딜러보다_점수가_높으면_플레이어의_승리다`() {
        deck = FakeDeck(
            listOf<Card>(
                Card(Rank.K, Suit.SPADE), // 10
                Card(Rank.Q, Suit.HEART), // 10
                Card(Rank.TWO, Suit.SPADE), // 12
                Card(Rank.TWO, Suit.CLUB), // 12
                Card(Rank.NINE, Suit.SPADE), // 21
                Card(Rank.EIGHT, Suit.SPADE)
            ))
        assertSimpleTest {
            run("y", "n", "2")
            assertThat(output()).contains(
                "블랙잭 게임을 시작합니다.",
                "플레이어 카드: [K, 2] - 현재 점수: 12",
                "딜러 카드: [Q, ?] - 현재 점수: ?",
                "카드를 더 받으시겠습니까? (Y/N)",
                "플레이어가 카드를 한 장 더 받았습니다",
                "플레이어 카드: [K, 2, 9] - 현재 점수: 21",
                "카드를 더 받으시겠습니까? (Y/N)",
                "딜러의 턴을 시작합니다.",
                "딜러 카드: [Q, 2] - 현재 점수: 12",
                "딜러가 카드를 한 장 더 받습니다.",
                "딜러 카드: [Q, 2, 8] - 현재 점수: 20",
                "플레이어의 승리입니다.",
                "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요."
            )
        }
    }

    @Test()
    fun `딜러가_처음_받은_2장의_카드가_17점_이상일떄_플레이어가_딜러보다_점수가_높으면_플레이어의_승리다`() {
        deck = FakeDeck(
            listOf<Card>(
                Card(Rank.K, Suit.SPADE), // 10
                Card(Rank.Q, Suit.HEART), // 10
                Card(Rank.TWO, Suit.SPADE), // 12
                Card(Rank.J, Suit.CLUB), // 12
                Card(Rank.NINE, Suit.SPADE), // 21
                Card(Rank.EIGHT, Suit.SPADE)
            ))
        assertSimpleTest {
            run("y", "n", "2")
            assertThat(output()).contains(
                "블랙잭 게임을 시작합니다.",
                "플레이어 카드: [K, 2] - 현재 점수: 12",
                "딜러 카드: [Q, ?] - 현재 점수: ?",
                "카드를 더 받으시겠습니까? (Y/N)",
                "플레이어가 카드를 한 장 더 받았습니다",
                "플레이어 카드: [K, 2, 9] - 현재 점수: 21",
                "카드를 더 받으시겠습니까? (Y/N)",
                "딜러의 턴을 시작합니다.",
                "딜러 카드: [Q, J] - 현재 점수: 20",
                "플레이어의 승리입니다.",
                "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요."
            )
        }
    }

    @Test()
    fun `딜러가_플레이어보다_점수가_높으면_딜러의_승리다`() {
        deck = FakeDeck(
            listOf<Card>(
                Card(Rank.K, Suit.SPADE),
                Card(Rank.Q, Suit.HEART),
                Card(Rank.TWO, Suit.SPADE),
                Card(Rank.TWO, Suit.CLUB),
                Card(Rank.EIGHT, Suit.SPADE),
                Card(Rank.NINE, Suit.SPADE)
            ))
        assertSimpleTest {
            run("y", "n", "2")
            assertThat(output()).contains(
                "블랙잭 게임을 시작합니다.",
                "플레이어 카드: [K, 2] - 현재 점수: 12",
                "딜러 카드: [Q, ?] - 현재 점수: ?",
                "카드를 더 받으시겠습니까? (Y/N)",
                "플레이어가 카드를 한 장 더 받았습니다",
                "플레이어 카드: [K, 2, 8] - 현재 점수: 20",
                "카드를 더 받으시겠습니까? (Y/N)",
                "딜러의 턴을 시작합니다.",
                "딜러 카드: [Q, 2] - 현재 점수: 12",
                "딜러가 카드를 한 장 더 받습니다.",
                "딜러 카드: [Q, 2, 9] - 현재 점수: 21",
                "딜러의 승리입니다.",
                "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요."
            )
        }
    }

    @Test()
    fun `딜러와_플레이어의_점수가_같으면_무승부다`() {
        deck = FakeDeck(
            listOf<Card>(
                Card(Rank.K, Suit.SPADE),
                Card(Rank.K, Suit.HEART),
                Card(Rank.TWO, Suit.SPADE),
                Card(Rank.TWO, Suit.CLUB),
                Card(Rank.EIGHT, Suit.SPADE),
                Card(Rank.EIGHT, Suit.SPADE)
            ))
        assertSimpleTest {
            run("y", "n", "2")
            assertThat(output()).contains(
                "블랙잭 게임을 시작합니다.",
                "플레이어 카드: [K, 2] - 현재 점수: 12",
                "딜러 카드: [K, ?] - 현재 점수: ?",
                "카드를 더 받으시겠습니까? (Y/N)",
                "플레이어가 카드를 한 장 더 받았습니다",
                "플레이어 카드: [K, 2, 8] - 현재 점수: 20",
                "카드를 더 받으시겠습니까? (Y/N)",
                "딜러의 턴을 시작합니다.",
                "딜러 카드: [K, 2] - 현재 점수: 12",
                "딜러가 카드를 한 장 더 받습니다.",
                "딜러 카드: [K, 2, 8] - 현재 점수: 20",
                "무승부입니다.",
                "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요."
            )
        }
    }


    @Test()
    fun `게임이_여러번_ 실행_될수_있다`() {
        deck = FakeDeck(
            listOf<Card>(
                Card(Rank.K, Suit.SPADE),
                Card(Rank.K, Suit.HEART),
                Card(Rank.TWO, Suit.SPADE),
                Card(Rank.TWO, Suit.CLUB),
                Card(Rank.EIGHT, Suit.SPADE),
                Card(Rank.EIGHT, Suit.SPADE),
                Card(Rank.K, Suit.SPADE),
                Card(Rank.K, Suit.HEART),
                Card(Rank.TWO, Suit.SPADE),
                Card(Rank.TWO, Suit.CLUB),
                Card(Rank.EIGHT, Suit.SPADE),
                Card(Rank.EIGHT, Suit.SPADE),
            ))
        assertSimpleTest {
            run("y", "n", "1", "y", "n", "2")
            assertThat(output()).contains(
                "블랙잭 게임을 시작합니다.",
                "플레이어 카드: [K, 2] - 현재 점수: 12",
                "딜러 카드: [K, ?] - 현재 점수: ?",
                "카드를 더 받으시겠습니까? (Y/N)",
                "플레이어가 카드를 한 장 더 받았습니다",
                "플레이어 카드: [K, 2, 8] - 현재 점수: 20",
                "카드를 더 받으시겠습니까? (Y/N)",
                "딜러의 턴을 시작합니다.",
                "딜러 카드: [K, 2] - 현재 점수: 12",
                "딜러가 카드를 한 장 더 받습니다.",
                "딜러 카드: [K, 2, 8] - 현재 점수: 20",
                "무승부입니다.",
                "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.",
                "블랙잭 게임을 시작합니다.",
                "플레이어 카드: [K, 2] - 현재 점수: 12",
                "딜러 카드: [K, ?] - 현재 점수: ?",
                "카드를 더 받으시겠습니까? (Y/N)",
                "플레이어가 카드를 한 장 더 받았습니다",
                "플레이어 카드: [K, 2, 8] - 현재 점수: 20",
                "카드를 더 받으시겠습니까? (Y/N)",
                "딜러의 턴을 시작합니다.",
                "딜러 카드: [K, 2] - 현재 점수: 12",
                "딜러가 카드를 한 장 더 받습니다.",
                "딜러 카드: [K, 2, 8] - 현재 점수: 20",
                "무승부입니다.",
                "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.",
            )
        }
    }


}