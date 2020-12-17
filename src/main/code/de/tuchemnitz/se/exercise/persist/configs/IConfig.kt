package de.tuchemnitz.se.exercise.persist.configs

import de.tuchemnitz.se.exercise.persist.IPersist
import org.litote.kmongo.Id
import java.time.Instant

interface IConfig : IPersist {
    override val _id: Id<out IConfig>
    val savedAt: Instant
}
