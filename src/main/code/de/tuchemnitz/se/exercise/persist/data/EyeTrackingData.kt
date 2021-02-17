package de.tuchemnitz.se.exercise.persist.data

import kotlinx.serialization.Contextual
import kotlinx.serialization.Transient
import org.litote.kmongo.Id
import org.litote.kmongo.newId
import java.time.Instant

data class EyeTrackingData(
    @Transient override val savedAt: Instant = Instant.now(),
    override val _id: Id<EyeTrackingData> = newId(),
    val dummyData: String,
    @Contextual val currentUser: UserData
) : IData
