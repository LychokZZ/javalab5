package com.example;

import java.util.Objects;

/**
 * Class representing an animal in the zoo.
 */
public class Animall {
    private final String name;
    private final String species;
    private final int age;

    private Animall(Builder builder) {
        this.name = builder.name;
        this.species = builder.species;
        this.age = builder.age;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Animal{name='" + name + "', species='" + species + "', age=" + age + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animall animall = (Animall) o;
        return age == animall.age && Objects.equals(name, animall.name) && Objects.equals(species, animall.species);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, species, age);
    }

    public static class Builder {
        private String name;
        private String species;
        private int age;
        private final StringBuilder validationErrors = new StringBuilder();

        private static final int MIN_NAME_LENGTH = 3;
        private static final int MAX_NAME_LENGTH = 50;
        private static final int MIN_AGE = 0;
        private static final int MAX_AGE = 100;
        private static final String SPECIES_PATTERN = "^[A-Z][a-z]+( [a-z]+)*$"; // Наприклад: "Panthera leo"

        public Builder setName(String name) {
            if (name == null || name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
                validationErrors.append("Invalid name: '")
                        .append(name)
                        .append("' - must be between ")
                        .append(MIN_NAME_LENGTH)
                        .append(" and ")
                        .append(MAX_NAME_LENGTH)
                        .append(" characters.\n");
            } else {
                this.name = name;
            }
            return this;
        }

        public Builder setSpecies(String species) {
            if (species == null || !species.matches(SPECIES_PATTERN)) {
                validationErrors.append("Invalid species: '")
                        .append(species)
                        .append("' - must match pattern '")
                        .append(SPECIES_PATTERN)
                        .append("'.\n");
            } else {
                this.species = species;
            }
            return this;
        }

        public Builder setAge(int age) {
            if (age < MIN_AGE || age > MAX_AGE) {
                validationErrors.append("Invalid age: '")
                        .append(age)
                        .append("' - must be between ")
                        .append(MIN_AGE)
                        .append(" and ")
                        .append(MAX_AGE)
                        .append(".\n");
            } else {
                this.age = age;
            }
            return this;
        }

        public Animall build() {
            if (validationErrors.length() > 0) {
                throw new IllegalArgumentException("Invalid fields:\n" + validationErrors.toString());
            }
            return new Animall(this);
        }
    }
}
