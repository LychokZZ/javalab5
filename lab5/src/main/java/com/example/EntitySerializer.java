package com.example;

/**
 * Interface for serializing and deserializing entities to/from various formats.
 *
 * @param <T> the type of the entity
 */
public interface EntitySerializer<T> {
    String serialize(T entity); // Залишаємо лише один параметр
    T deserialize(String data);
}
