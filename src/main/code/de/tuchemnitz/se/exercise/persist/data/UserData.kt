package de.tuchemnitz.se.exercise.persist.data

import de.tuchemnitz.se.exercise.persist.now
import kotlinx.serialization.Transient
import org.litote.kmongo.Id
import org.litote.kmongo.newId
import java.time.Instant

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
    @Transient override val savedAt: Instant = now(),
    override val _id: Id<UserData> = newId(),
    val firstName: String = "default",
    val lastName: String = "default",
    val age: Int = 0,
    val gender: Gender = Gender.Unselected,
    val visionImpaired: VisionImpaired = VisionImpaired.Unselected,
    val default: Boolean
) : IData
