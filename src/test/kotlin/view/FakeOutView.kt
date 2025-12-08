package view

class FakeOutView(val promptMessages: MutableList<String>) : OutView {

    override fun startPrompt() {
        promptMessages.add("블랙잭 게임을 시작합니다.")
    }

}