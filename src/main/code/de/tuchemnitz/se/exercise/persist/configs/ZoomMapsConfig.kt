package de.tuchemnitz.se.exercise.persist.configs

import org.litote.kmongo.Id
import org.litote.kmongo.newId
import java.time.Instant

data class ZoomMapsConfig(
    override val _id: Id<ZoomMapsConfig> = newId(),
    val zoomSpeed: Float,
    val keyBindings: Set<String>,
    override val savedAt: Instant = Instant.now()
) : IConfig
