import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost/cacaulotedb";
        String user = "root";
        String password = "2525";

        try (Connection conexao = DriverManager.getConnection(url, user, password);
                Statement stmt = conexao.createStatement();
                ResultSet rsCacauDados = stmt.executeQuery("SELECT * FROM blocoGenesis")) {

            while (rsCacauDados.next()) {
                String proprietario = rsCacauDados.getString("nome_proprietario");
                double latitude = rsCacauDados.getDouble("latitude_plantacao");

                System.out.println("Proprietario : " + proprietario);
                System.out.println("Latitude : " + latitude);
            }

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao acessar o banco: " + e.getMessage());
        }
    }
}
