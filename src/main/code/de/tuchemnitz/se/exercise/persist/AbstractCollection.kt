package de.tuchemnitz.se.exercise.persist

import com.mongodb.client.FindIterable
import org.bson.BsonDocument
import org.bson.conversions.Bson
import org.litote.kmongo.Id
import org.litote.kmongo.deleteOneById
import org.litote.kmongo.findOneById
import tornadofx.Controller
import kotlin.reflect.KClass

/**
 * Keeps all the database functions. All functions can be overridden by its instances, in case modification is needed
 * @param T of type IPersist, only allows creation of collection for types extended by IPersist.
 * @param clazz - KClass of instance extended by IPersist, used to get the collection matching to that instance.
 */
abstract class AbstractCollection<T : IPersist>(
    clazz: KClass<T>
) : Controller() {
    private val database: Database by inject()
    private val collection = database.getCollection(clazz)

    /**
     * Finds elements matching to a certain filter
     * @param filter of type Bson, which is used to set filters for the find function
     */
    open fun find(filter: Bson = BsonDocument()): FindIterable<T> {
        return collection.find(filter)
    }

    /**
     * Finds element matching to a certain id
     * @param id - is being created in instances of IPersist, used to search for a specific entry.
     */
    open fun findOneById(id: Id<T>): T? {
        return collection.findOneById(id)
    }

    /**
     * Saves data of type IPersist
     * @param data - data which is being saved
     */
    open fun saveOne(data: T) {
        collection.insertOne(data)
    }

    /**
     * Saves multiple data entries of type IPersist
     * @param data - set of entries which is being saved
     */
    open fun saveMany(data: Set<T>) {
        collection.insertMany(data.toList())
    }

    /**
     * Deletes element matching to a certain id
     * @param filter - filter of type Bson, which is used to set filters for deletion. First found is deleted.
     */
    open fun deleteOne(filter: Bson) {
        collection.deleteOne(filter)
    }

    /**
     * Deletes elements matching to a certain filter
     * @param filter of type Bson, which is used to set filters for deletion
     */
    open fun deleteMany(filter: Bson = BsonDocument()) {
        collection.deleteMany(filter)
    }
}
