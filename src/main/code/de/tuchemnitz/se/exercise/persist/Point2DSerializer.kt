package de.tuchemnitz.se.exercise.persist

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.node.ObjectNode
import javafx.geometry.Point2D
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure

object Point2DSerializer : JsonDeserializer<Point2D>(), KSerializer<Point2D> {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Point2D {
        val node = p.readValueAsTree<ObjectNode>()
        return Point2D(node["x"].asDouble(), node["y"].asDouble())
    }

    override fun deserialize(decoder: Decoder): Point2D {
        return decoder.decodeStructure(descriptor) {
            var x: Double? = null
            var y: Double? = null
            while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    0 -> x = decodeDoubleElement(descriptor, 0)
                    1 -> y = decodeDoubleElement(descriptor, 1)
                    CompositeDecoder.DECODE_DONE -> break
                    else -> error("Unexpected index: $index")
                }
            }
            return@decodeStructure Point2D(requireNotNull(x), requireNotNull(y))
        }
    }

    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Point2D") {
        element<Double>("x")
        element<Double>("y")
    }

    override fun serialize(encoder: Encoder, value: Point2D) {
        encoder.encodeStructure(descriptor) {
            encodeDoubleElement(descriptor, 0, value.x)
            encodeDoubleElement(descriptor, 1, value.y)
        }
    }
}