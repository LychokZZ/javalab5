package com.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Animall animall = new Animall.Builder()
                    .setName("Li") // Невалідне ім'я (занадто коротке)
                    .setSpecies("Panthera leo")
                    .setAge(150) // Невалідний вік (занадто великий)
                    .build();
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to create Animal: " + e.getMessage());
        }
        // Створення бази даних та таблиці, якщо вони не існують
        DatabaseManager.createDatabaseIfNotExists();
        DatabaseManager.createTableIfNotExists();

        // Створення нових записів
        AnimalDAO animalDAO = new AnimalDAO();
        animalDAO.createAnimal(new Animal("Lion", "Panthera leo", 5));
        animalDAO.createAnimal(new Animal("Tiger", "Panthera tigris", 3));

        // Читання записів
        System.out.println("Reading all animals:");
        animalDAO.readAllAnimals();

        // Оновлення запису
        // Створюємо об'єкт Animal для оновлення
        Animal animalToUpdate = new Animal(1, "Lion", "Panthera leo", 6);

        // Оновлюємо тварину
        AnimalDAO AnimalDAO = new AnimalDAO();
        animalDAO.updateAnimal(animalToUpdate);

        // Видалення запису
        animalDAO.deleteAnimal(2);
    }
}

