package de.tuchemnitz.se.exercise.persist.configs

import de.tuchemnitz.se.exercise.persist.IPersist
import org.litote.kmongo.Id
import java.time.Instant

/**
 * Interface to specify persist object, in this case IConfig
 */
interface IConfig : IPersist {
    override val _id: Id<out IConfig>
    val savedAt: Instant
}
