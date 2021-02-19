package de.tuchemnitz.se.exercise.persist

import com.mongodb.client.MongoIterable
import org.litote.kmongo.`in`
import org.litote.kmongo.insertOne
import org.litote.kmongo.projection
import org.litote.kmongo.withDocumentClass
import java.nio.file.Path

class ImageCollection : AbstractCollection<Image>(Image::class) {
    private data class ImageNameOnly(val name: String)

    companion object {
        val DEFAULT_IMAGES = setOf("Chameleon", "Penguin", "Kitten", "Background")

        fun defaultImagePath(imageName: String): Path = Path.of("src/main/resources/$imageName.jpg")
    }

    init {
        ensureDefaultImages()
    }

    fun existingDefaultImages(): MongoIterable<String> {
        return collection.withDocumentClass<ImageNameOnly>()
            .find(ImageNameOnly::name `in` DEFAULT_IMAGES)
            .projection(ImageNameOnly::name)
            .map { it.name }
    }

    private fun ensureDefaultImages() {
        val existingImages = existingDefaultImages().toSet()
        val missingImages = DEFAULT_IMAGES - existingImages
        missingImages.map { insertOne(Image(name = it, path = defaultImagePath(it))) }
            .takeIf { it.isNotEmpty() }
            ?.run(collection::bulkWrite)
    }
}