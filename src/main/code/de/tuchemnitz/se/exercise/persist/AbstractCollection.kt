package de.tuchemnitz.se.exercise.persist

import com.mongodb.client.FindIterable
import com.mongodb.client.MongoCollection
import org.bson.BsonDocument
import org.bson.conversions.Bson
import org.litote.kmongo.findOneById

abstract class AbstractCollection<T : IPersist>(
    private val collection: MongoCollection<T>
) {

    open fun find(filter: Bson): FindIterable<T> {
        return collection.find(filter)
    }

    open fun findOneById(filter: Any): T? {
        return collection.findOneById(filter)
    }

    open fun saveOne(data: T) {
        collection.insertOne(data)
    }

    open fun saveMany(data: Set<T>) {
        collection.insertMany(data.toList())
    }

    open fun deleteOne(filter: Bson) {
        collection.deleteOne(filter)
    }

    open fun deleteMany(filter: Bson = BsonDocument()) {
        collection.deleteMany(filter)
    }
}