package com.bse.homeshop.dao;

import com.bse.homeshop.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    // Connection data
    private final String url = "jdbc:mysql://localhost/homeshop";
    private final String user = "root";
    private final String pwd = "Tinez2012";

    /**
     * Get all products from the database
     * @return product list
     */
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(url, user, pwd);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from product");

            while(resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                Product product = new Product(name, description, price);
                products.add(product);
            }
            connection.close();
        } catch (SQLException e) {
            System.err.println("Problème de connexion à la base de données.");
            e.printStackTrace();
        }
        return products;
    }
}
