import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {

    public static void main(String[] args) {

        String url = "jdbc:mysql://cacau-lote-db.mysql.database.azure.com:3306/teste_db?useSSL=true";
        String user = "user";
        String password = "cacau#Lote";

        try (Connection conexao = DriverManager.getConnection(url, user, password);
                Statement stmt = conexao.createStatement();
                ResultSet rsCacauDados = stmt.executeQuery("SELECT * FROM blocogenesis")) {

            while (rsCacauDados.next()) {
                String proprietario = rsCacauDados.getString("proprietario");
                double latitude = rsCacauDados.getDouble("latitude_plantacao");

                System.out.println("Proprietario : " + proprietario);
                System.out.println("Latitude : " + latitude);
            }

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao acessar o banco: " + e.getMessage());
        }
    }
}
