package de.tu_chemnitz.se.persist.configs

import de.tu_chemnitz.se.persist.IConfig

data class ZoomMapsConfig(
  val zoomSpeed : Float,
  val keyBindings : Set<String>
) : IConfig