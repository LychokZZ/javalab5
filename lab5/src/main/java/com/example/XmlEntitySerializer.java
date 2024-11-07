package com.example;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * Serializer for XML format.
 */
public class XmlEntitySerializer<T> implements EntitySerializer<T> {
    private final Class<T> entityType;
    private final XmlMapper xmlMapper = new XmlMapper();

    public XmlEntitySerializer(Class<T> entityType) {
        this.entityType = entityType;
    }

    @Override
    public String serialize(T entity) {
        try {
            return xmlMapper.writeValueAsString(entity);
        } catch (Exception e) {
            throw new RuntimeException("Error serializing entity to XML", e);
        }
    }

    @Override
    public T deserialize(String data) {
        try {
            return xmlMapper.readValue(data, entityType);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing XML to entity", e);
        }
    }
}
