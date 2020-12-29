package de.tuchemnitz.se.exercise.core

import javafx.stage.Stage
import tornadofx.App
import tornadofx.View
import kotlin.reflect.KClass

abstract class AbstractTool<VIEW : View>(viewClass: KClass<VIEW>) : App(primaryView = viewClass) {

    abstract override fun start(stage: Stage)
}
