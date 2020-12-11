package de.tuchemnitz.se.exercise.persist.configs

import org.litote.kmongo.Id
import org.litote.kmongo.newId
import java.time.Instant

// TODO:
data class EyeTrackingConfig(
    override val _id: Id<EyeTrackingConfig> = newId(),
    val dummyVal: String,
    val savedAt: Instant = Instant.now()
) : IConfig
