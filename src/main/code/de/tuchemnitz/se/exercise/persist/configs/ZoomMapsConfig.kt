package de.tuchemnitz.se.exercise.persist.configs

import javafx.scene.input.KeyCode
import org.litote.kmongo.Id
import org.litote.kmongo.newId
import tornadofx.ScopedInstance

data class ZoomMapsConfig(
    override val _id: Id<ZoomMapsConfig> = newId(),
    val zoomSpeed: Float,
    val zoomKey: KeyCode
) : IConfig, ScopedInstance
