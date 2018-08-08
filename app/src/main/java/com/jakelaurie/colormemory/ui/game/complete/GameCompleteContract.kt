package com.jakelaurie.colormemory.ui.game.complete

import com.jakelaurie.colormemory.ui.BaseView

interface GameCompleteContract {
    interface View: BaseView {
        fun displayPoints(points: Int, isHighScore: Boolean)
        fun onNameError()
        fun onHighscoreAdded()
    }

    interface Presenter {
        fun onNameEntered(value: String)
    }

    interface Callback {
        fun showHighscores()
    }
}