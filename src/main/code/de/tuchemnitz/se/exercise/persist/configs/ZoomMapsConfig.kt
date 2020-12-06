package de.tuchemnitz.se.exercise.persist.configs

import de.tu_chemnitz.se.exercise.persist.IConfig
import org.litote.kmongo.Id
import org.litote.kmongo.newId

data class ZoomMapsConfig(
    override val _id: Id<ZoomMapsConfig> = newId(),
    val zoomSpeed: Float,
    val keyBindings: Set<String>
) : IConfig
