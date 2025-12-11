import camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest
import camp.nextstep.edu.missionutils.test.NsTest
import com.taeyong.blackjack.main
import kotlin.test.Test

class ApplicationTest : NsTest() {
    override fun runMain() {
        main()
    }

    @Test
    fun `정상 실행`() {
        assertSimpleTest {
            run("y", "y", "n")
        }
    }

}