package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Serializer for JSON format.
 */
public class JsonEntitySerializer<T> implements EntitySerializer<T> {
    private final Class<T> entityType;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JsonEntitySerializer(Class<T> entityType) {
        this.entityType = entityType;
    }

    @Override
    public String serialize(T entity) {
        try {
            return objectMapper.writeValueAsString(entity);
        } catch (Exception e) {
            throw new RuntimeException("Error serializing entity to JSON", e);
        }
    }

    @Override
    public T deserialize(String data) {
        try {
            return objectMapper.readValue(data, entityType);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing JSON to entity", e);
        }
    }
}
