import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        findById();

    }
    public static void connectToDatabase() {
        try (Connection connection = DB.connect()) {
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void createTable() {
        var sql = "CREATE TABLE products (" +
                "    id SERIAL PRIMARY KEY," +
                "    name VARCHAR(255) NOT NULL," +
                "    price DECIMAL(10,2) NOT NULL" +
                ");";
        try (var connection = DB.connect()) {
            var statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void insertProduct() {
        int id = ProductDB.add(new Product("Camera Digital", 1499.99));
        System.out.println("Inserted id: " + id);
    }

    public static void insertProducts() {
        var products = new ArrayList<Product>();
        products.add(new Product("Tripé", 99.99));
        products.add(new Product("Roteador wireless", 199.99));
        products.add(new Product("Mouse", 49.99));
        products.add(new Product("Teclado", 49.99));
        products.add(new Product("Monitor", 999.99));
        products.add(new Product("Cadeira gamer", 1299.99));
        products.add(new Product("Mouse gamer", 199.99));
        products.add(new Product("Teclado gamer", 199.99));
        ProductDB.add(products);
    }

    public static void findAll() {
        var products = ProductDB.findAll();
        for (var product : products) {
            System.out.println(product);
        }
    }

    public static void findById() {
        var p = ProductDB.findById(1);
        if (p != null) {
            System.out.println(p);
        }
    }


}
