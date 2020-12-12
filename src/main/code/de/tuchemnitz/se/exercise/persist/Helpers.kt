package de.tuchemnitz.se.exercise.persist

import java.time.Instant
import java.time.temporal.ChronoUnit

fun now(): Instant = Instant.now().truncatedTo(ChronoUnit.SECONDS)
