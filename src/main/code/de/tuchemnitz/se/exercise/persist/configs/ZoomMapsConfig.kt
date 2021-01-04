package de.tuchemnitz.se.exercise.persist.configs

import javafx.scene.input.KeyCode
import org.litote.kmongo.Id
import org.litote.kmongo.newId
import tornadofx.Controller
import java.time.Instant

data class ZoomMapsConfig(
    override val _id: Id<ZoomMapsConfig> = newId(),
    override val savedAt: Instant,
    val zoomSpeed: Float,
    val zoomKey: KeyCode
) : IConfig, Controller()