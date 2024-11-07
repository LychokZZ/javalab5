package com.example;

import org.yaml.snakeyaml.Yaml;

/**
 * Serializer for YAML format.
 */
public class YamlEntitySerializer<T> implements EntitySerializer<T> {
    private final Class<T> entityType;
    private final Yaml yaml = new Yaml();

    public YamlEntitySerializer(Class<T> entityType) {
        this.entityType = entityType;
    }

    @Override
    public String serialize(T entity) {
        return yaml.dump(entity);
    }

    @Override
    public T deserialize(String data) {
        return yaml.loadAs(data, entityType);
    }
}
