package de.tuchemnitz.se.exercise.persist

import de.tuchemnitz.se.exercise.persist.data.UserData
import kotlinx.serialization.Transient
import org.litote.kmongo.Id
import org.litote.kmongo.newId
import java.nio.file.Path
import java.time.Instant

data class Image(
    override val _id: Id<UserData> = newId(),
    @Transient override val savedAt: Instant = now(),
    val name: String,
    val path: Path
) : IPersist
