import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {

    public static void main(String[] args) {

        // String url = "jdbc:mysql://localhost/cacaulotedb";
        // String user = "root";
        // String password = "2525";

        // (Connection conexao = DriverManager.getConnection(url, user, password);
        // Statement stmt = conexao.createStatement();
        // ResultSet rsCacauDados = stmt.executeQuery("SELECT * FROM blocoGenesis"))

        Connection conexao = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost/cacaulotedb", "root", "2525");
            ResultSet rsBlocoGenesis = conexao.createStatement().executeQuery("SELECT * FROM blocoGenesis");
            while (rsBlocoGenesis.next()) {
                String proprietario = rsBlocoGenesis.getString("nome_proprietario");
                double latitude = rsBlocoGenesis.getDouble("latitude_plantacao");

                System.out.println("Proprietario : " + proprietario);
                System.out.println("Latitude : " + latitude);
            }

        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao acessar o banco: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver do banco de dados não localizado");
        }
    }
}
