package de.tuchemnitz.se.exercise.persist

import com.mongodb.client.MongoDatabase
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.litote.kmongo.KMongo

@TestInstance(Lifecycle.PER_CLASS)
abstract class DatabaseTestBase<Collection : AbstractCollection<out IPersist>>(
    collection: (MongoDatabase) -> Collection
) {
    private val client = KMongo.createClient()
    private val database = client.getDatabase("test")
    protected val collection = collection(database)

    @AfterEach
    fun cleanUp() {
        collection.deleteMany()
    }
}
