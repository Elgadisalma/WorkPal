import java.sql.Connection;
import java.sql.DriverManager;

public class PostgreSQLConnection {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/WorkPal";
        String user = "postgres";
        String password = "123";

        Connection conn = null;

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion réussite");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
