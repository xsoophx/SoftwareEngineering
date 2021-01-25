package de.tuchemnitz.se.exercise.persist

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.node.ObjectNode
import javafx.geometry.Point2D

class Point2DDeserializer : JsonDeserializer<Point2D>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Point2D {
        val node = p.readValueAsTree<ObjectNode>()
        return Point2D(node["x"].asDouble(), node["y"].asDouble())
    }
}
