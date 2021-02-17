package de.tuchemnitz.se.exercise.persist.configs

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.litote.kmongo.Id
import org.litote.kmongo.newId
import java.time.Instant

// TODO:
@Serializable
data class EyeTrackingConfig(
    @Transient override val _id: Id<EyeTrackingConfig> = newId(),
    @Transient override val savedAt: Instant = Instant.now(),
    val pictures: List<Pair<String, Int>>
) : IConfig
