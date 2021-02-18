package de.tuchemnitz.se.exercise.persist.data

import de.tuchemnitz.se.exercise.persist.now
import kotlinx.serialization.Transient
import org.litote.kmongo.Id
import org.litote.kmongo.newId
import java.time.Instant
import java.util.Dictionary
import java.util.Hashtable

enum class Gender {
    Diverse,
    Male,
    Female,
    Unselected
}

enum class VisionImpaired {
    Yes,
    No,
    Unselected
}

data class UserData(
    override val _id: Id<UserData> = newId(),
    @Transient override val savedAt: Instant = now(),
    val firstName: String = "default",
    val lastName: String = "default",
    val age: Int = 0,
    val gender: Gender = Gender.Unselected,
    val visionImpaired: VisionImpaired = VisionImpaired.Unselected,
    val default: Boolean
) : IData
