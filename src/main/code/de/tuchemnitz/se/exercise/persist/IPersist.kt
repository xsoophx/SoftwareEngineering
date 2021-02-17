package de.tuchemnitz.se.exercise.persist

import org.litote.kmongo.Id
import java.time.Instant

interface IPersist {
    val _id: Id<out IPersist>
    val savedAt: Instant
}



