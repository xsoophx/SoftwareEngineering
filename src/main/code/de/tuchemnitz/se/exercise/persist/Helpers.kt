package de.tuchemnitz.se.exercise.persist

import java.time.Instant
import java.time.temporal.ChronoUnit

/**
 * Creates the current time (when called) in milliseconds
 */
fun now(): Instant = Instant.now().truncatedTo(ChronoUnit.MILLIS)
