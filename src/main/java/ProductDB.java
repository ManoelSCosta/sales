import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDB {
    public static int add(Product product) {
        var sql = "INSERT INTO products (name, price)" +
                "  VALUES (?, ?)";
        try (var conn = DB.connect();
            var pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getPrice());

            int insertedRows = pstmt.executeUpdate();
            if (insertedRows > 0) {
                var rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void add(List<Product> products) {
        var sql = "INSERT INTO products (name, price)" +
                "  VALUES (?, ?)";

        try (var conn = DB.connect();
            var pstmt = conn.prepareStatement(sql)) {
            for (var product : products) {
                pstmt.setString(1, product.getName());
                pstmt.setDouble(2, product.getPrice());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Product> findAll() {
        var products = new ArrayList<Product>();
        var sql = "SELECT id, name, price FROM products ORDER BY name";
        try (var conn = DB.connect();
            var stmt = conn.createStatement()) {

            var rs = stmt.executeQuery(sql);

            while (rs.next()) {
                var product = new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public static Product findById(int id) {
        var sql = "SELECT id, name, price FROM products WHERE id = ?";
        try (var conn = DB.connect();
             var pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, id);

            var rs = pstmt.executeQuery();

            if (rs.next()){
                return new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static int update(int id, String name, double price) {
        var sql = "UPDATE products " +
                "SET name = ?, price = ? " +
                "WHERE id = ?";
        int affectedRows = 0;

        try (var conn = DB.connect();
             var pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.setInt(3, id);
            affectedRows = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows;
    }

    public static int delete(int id) {
        var sql = "DELETE FROM products WHERE id = ?";

        try (var conn = DB.connect();
             var pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static List<Product> findByName(String name) {
        String SQL = "SELECT * FROM find_products (?)";

        var products = new ArrayList<Product>();

        try (var conn = DB.connect();
             var pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, name);
            var rs = pstmt.executeQuery();

            while (rs.next()) {
                var product = new Product(
                        rs.getInt("p_id"),
                        rs.getString("p_name"),
                        rs.getDouble("p_price"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}

