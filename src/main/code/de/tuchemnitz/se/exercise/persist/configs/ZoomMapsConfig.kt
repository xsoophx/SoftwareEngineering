package de.tuchemnitz.se.exercise.persist.configs

import de.tuchemnitz.se.exercise.persist.now
import javafx.scene.input.KeyCode
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.litote.kmongo.Id
import org.litote.kmongo.newId
import java.time.Instant

val DEFAULT_ZOOM_KEY = KeyCode.C
const val DEFAULT_ZOOM_SPEED = 1.0

@Serializable
data class ZoomMapsConfig(
    @Transient override val _id: Id<ZoomMapsConfig> = newId(),
    @Transient override val savedAt: Instant = now(),
    val zoomSpeed: Double = DEFAULT_ZOOM_SPEED,
    val zoomKey: KeyCode = DEFAULT_ZOOM_KEY
) : IConfig
