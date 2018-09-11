package com.example.lucas.spidey2.internal.extensions

import java.util.*

/**
 * Returns a random element.
 */
fun <E> List<E>.random(): E = get(Random().nextInt(size))