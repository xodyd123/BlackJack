package controller


import view.OutView

class GameController(private val outView: OutView) {

    fun run() {
        outView.startPrompt()
//        game.start()
    }
}