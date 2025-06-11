package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class Conexion {

   public static Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost/ferreteria_db";

            Connection cn = DriverManager.getConnection(url, "root", "1234");
            return cn;
        } catch (ClassNotFoundException e) {
            System.err.println("Driver de MySQL no encontrado: " + e);
        } catch (SQLException e) {
            System.err.println("Error en la conexión local: " + e);
        }
        return null;
    }
}
