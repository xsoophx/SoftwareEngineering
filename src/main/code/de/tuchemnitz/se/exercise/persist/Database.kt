package de.tuchemnitz.se.exercise.persist

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.litote.kmongo.KMongo
import org.litote.kmongo.util.KMongoUtil.defaultCollectionName
import tornadofx.Controller
import kotlin.reflect.KClass

const val DATABASE_NAME = "test"

class Database(databaseName: String = DATABASE_NAME) : Controller() {
    private val client = KMongo.createClient()
    val database: MongoDatabase = client.getDatabase(databaseName)

    /**
     * Deletes elements matching to a certain filter
     * @param T of type IPersist, which is used to return the Collection matching to that entity.
     */
    fun <T : IPersist> getCollection(clazz: KClass<T>): MongoCollection<T> {
        return database.getCollection(defaultCollectionName(clazz), clazz.java)
    }
}
