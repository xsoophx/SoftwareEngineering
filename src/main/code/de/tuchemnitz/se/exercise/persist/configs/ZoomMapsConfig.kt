package de.tuchemnitz.se.exercise.persist.configs

import de.tuchemnitz.se.exercise.persist.now
import javafx.scene.input.KeyCode
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.litote.kmongo.Id
import org.litote.kmongo.newId
import java.time.Instant

@Serializable
data class ZoomMapsConfig(
    @Transient override val _id: Id<ZoomMapsConfig> = newId(),
    @Transient override val savedAt: Instant = now(),
    val zoomSpeed: Float,
    val zoomKey: KeyCode
) : IConfig