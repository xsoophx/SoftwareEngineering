package de.tuchemnitz.se.exercise.persist.configs

import de.tuchemnitz.se.exercise.persist.now
import kotlinx.serialization.ContextualSerialization
import kotlinx.serialization.Serializable
import org.litote.kmongo.Id
import org.litote.kmongo.newId
import java.time.Instant

@Serializable
data class ZoomMapsConfig(
    override val _id: Id<ZoomMapsConfig> = newId(),
    @ContextualSerialization override val savedAt: Instant = now(),
    val zoomSpeed: Float,
    val keyBindings: Set<String>
) : IConfig
