package com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO {
    public void createAnimal(Animal animal) {
        String sql = "INSERT INTO animals (name, species, age) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, animal.getName());
            stmt.setString(2, animal.getSpecies());
            stmt.setInt(3, animal.getAge());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Animal readAnimal(int id) {
        String query = "SELECT * FROM animals WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Створюємо об'єкт Animal, використовуючи конструктор з id
                return new Animal(rs.getInt("id"), rs.getString("name"), rs.getString("species"), rs.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Animal> readAllAnimals() {
        List<Animal> animals = new ArrayList<>();
        String sql = "SELECT * FROM animals";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                animals.add(new Animal(rs.getInt("id"), rs.getString("name"), rs.getString("species"), rs.getInt("age")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animals;
    }

    public void updateAnimal(Animal animal) {
        String query = "UPDATE animals SET name = ?, species = ?, age = ? WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Встановлюємо параметри в PreparedStatement
            stmt.setString(1, animal.getName());
            stmt.setString(2, animal.getSpecies());
            stmt.setInt(3, animal.getAge());
            stmt.setInt(4, animal.getId());

            // Виконання оновлення в базі даних
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Animal updated successfully!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAnimal(int id) {
        String sql = "DELETE FROM animal WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
