package de.tuchemnitz.se.exercise.persist.configs

import org.litote.kmongo.Id
import org.litote.kmongo.newId

// TODO:
data class EyeTrackingConfig(
    override val _id: Id<EyeTrackingConfig> = newId(),
    val dummyVal: String
) : IConfig
