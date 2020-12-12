package de.tuchemnitz.se.exercise.persist.configs

import de.tuchemnitz.se.exercise.persist.now
import org.litote.kmongo.Id
import org.litote.kmongo.newId
import java.time.Instant

data class ZoomMapsConfig(
    override val _id: Id<ZoomMapsConfig> = newId(),
    override val savedAt: Instant = now(),
    val zoomSpeed: Float,
    val keyBindings: Set<String>
) : IConfig
