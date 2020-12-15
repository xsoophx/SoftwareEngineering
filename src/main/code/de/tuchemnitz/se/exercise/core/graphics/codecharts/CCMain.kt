package de.tuchemnitz.se.exercise.core.graphics.codecharts

import de.tuchemnitz.se.exercise.core.graphics.codecharts.CCPictureView
import javafx.stage.Stage
import tornadofx.App
import tornadofx.importStylesheet

class CCMain : App() {
  override val primaryView = CCPictureView::class

  override fun start(stage: Stage) {
    super.start(stage)
    stage.isMaximized = true
    stage.isResizable = false
  }

  init {
    importStylesheet(CCStyle::class)
  }
}
