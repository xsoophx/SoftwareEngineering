package de.tuchemnitz.se.exercise.core

import javafx.stage.Stage
import tornadofx.App
import tornadofx.View
import kotlin.reflect.KClass

abstract class AbstractTool(primaryView: KClass<out View>) : App(primaryView = primaryView) {

    open fun setup(stage: Stage) {
        stage.isResizable = true
        stage.minHeight = 850.0
        stage.minWidth = 1300.0
        stage.fullScreenExitHint = ""
    }

    override fun start(stage: Stage) {
        super.start(stage)
        setup(stage)
    }
}
