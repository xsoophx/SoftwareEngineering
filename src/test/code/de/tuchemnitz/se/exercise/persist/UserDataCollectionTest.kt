package de.tuchemnitz.se.exercise.persist

import assertk.assertThat
import assertk.assertions.containsOnly
import assertk.assertions.doesNotContain
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import com.mongodb.client.model.Filters
import de.tuchemnitz.se.exercise.persist.data.UserData
import de.tuchemnitz.se.exercise.persist.data.collections.UserDataCollection
import org.bson.conversions.Bson
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.litote.kmongo.KMongo
import org.litote.kmongo.`in`
import org.litote.kmongo.eq

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class UserDataCollectionTest {
    private val client = KMongo.createClient() // get com.mongodb.MongoClient new instance
    private val database = client.getDatabase("test") // normal java driver usage
    private val collection = UserDataCollection(database)

    @Suppress("SpellCheckingInspection")
    companion object {
        private val configs = setOf(
            UserData(firstName = "Testuser", surName = "Kotlin", age = 45),
            UserData(firstName = "anotherUser", surName = "Java", age = 25),
            UserData(firstName = "Santa", surName = "Claus", age = 65)
        )
    }

    @AfterEach
    fun cleanUp() {
        collection.deleteMany()
    }

    @Test
    fun `all configs should be saved properly at once`() {
        collection.saveMany(configs)
        assertThat(collection.find(UserData::_id `in` configs.map(UserData::_id)))
            .containsOnly(*configs.toTypedArray())
    }

    @Test
    fun `every config should be saved properly`() {
        configs.forEach {
            collection.saveOne(it)
            assertThat(collection.findOneById(it._id)).isEqualTo(it)
        }
    }

    @Test
    fun `every config should be deleted properly`() {
        configs.forEach {
            collection.deleteOne(UserData::_id eq it._id)
            assertThat(collection.find(UserData::_id eq it._id)).doesNotContain(it)
        }
    }

    @Test
    fun `findOneById should work properly for each id`() {
        collection.saveMany(configs)
        configs.forEach {
            assertThat(collection.findOneById(it._id)).isEqualTo(it)
        }
    }

    @Test
    fun `configs should be deleted properly at once`() {
        val ids = "_id" `in` configs
        collection.deleteMany(ids)

        configs.forEach {
            collection.deleteOne(UserData::_id eq it._id)
            assertThat(collection.findOneById(it._id)).isNull()
        }
    }

    private infix fun String.`in`(collection: Collection<Any>): Bson =
        Filters.`in`(this, collection)
}
