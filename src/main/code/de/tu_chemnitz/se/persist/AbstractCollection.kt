package de.tu_chemnitz.se.persist

import com.mongodb.client.FindIterable
import com.mongodb.client.MongoCollection
import org.bson.conversions.Bson

abstract class AbstractCollection<T : IConfig>(
  private val collection: MongoCollection<T>
) {

  open fun find(filter: Bson): FindIterable<T> {
    return collection.find(filter)
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

  open fun deleteMany(filter: Bson) {
    collection.deleteMany(filter)
  }

}

