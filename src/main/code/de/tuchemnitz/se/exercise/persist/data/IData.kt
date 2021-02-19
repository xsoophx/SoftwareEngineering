package de.tuchemnitz.se.exercise.persist.data

import de.tuchemnitz.se.exercise.persist.IPersist
import java.time.Instant

/**
 * Interface to specify persist object, in this case IData
 */
interface IData : IPersist {
    override val savedAt: Instant
}
