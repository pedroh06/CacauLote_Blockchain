package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws Exception {
        Blockchain blockchain = new Blockchain();

        blockchain.addBlock("Responsável 1");
        blockchain.addBlock("Responsável 2");
        blockchain.addBlock("Responsável 3");
        blockchain.addBlock("Responsável 4");
        blockchain.addBlock("Responsável 5");

        for (Block bloco : blockchain.getBlockchain()) {
            System.out.println(bloco.imprimirBloco());
        }

        Connection conexao = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://cacau-lote-db.mysql.database.azure.com:3306/cacaulotedb", "user", "cacau#Lote");
            ResultSet rsBlocoGenesis = conexao.createStatement().executeQuery("SELECT * FROM bloco_Genesis");
            while (rsBlocoGenesis.next()) {
                String proprietario = rsBlocoGenesis.getString("nome_proprietario");
                double latitude = rsBlocoGenesis.getDouble("latitude_plantacao");
                double longitude = rsBlocoGenesis.getDouble("longitude_plantacao");

                System.out.println("Proprietario : " + proprietario);
                System.out.println("Latitude : " + latitude);
                System.out.println("Longitude : " + longitude);
            }

        } catch (ClassNotFoundException ex) {
            System.out.println("Driver do banco de dados não localizado: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao acessar o banco: " + ex.getMessage());
        } finally {
            if (conexao != null) {
                conexao.close();
            }
        }
    }
}
