package de.tu_chemnitz.se.exercise.core.graphics

import tornadofx.App
import tornadofx.importStylesheet


class MainApp : App(){
  override val primaryView = MainView::class

  init {
    importStylesheet(Style::class)
  }
}
