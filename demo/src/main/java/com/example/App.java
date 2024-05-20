package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws Exception {
        Blockchain blockchain = new Blockchain();

        Connection conexao = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://cacau-lote-db.mysql.database.azure.com:3306/cacaulotedb", "user", "cacau#Lote");

            ResultSet rsBlocoGenesis = conexao.createStatement().executeQuery("SELECT * FROM bloco_Genesis");
            if (rsBlocoGenesis.next()) {
                String proprietario = rsBlocoGenesis.getString("nome_proprietario");
                String cpf = rsBlocoGenesis.getString("cpf_proprietario");
                double latitude = rsBlocoGenesis.getDouble("latitude_plantacao");
                double longitude = rsBlocoGenesis.getDouble("longitude_plantacao");
                String plantacaoInfos = rsBlocoGenesis.getString("informacoes");

                blockchain = new Blockchain(proprietario, cpf, latitude, longitude, plantacaoInfos);
            }

            ResultSet rsBlocos = conexao.createStatement().executeQuery("SELECT * FROM blocos ORDER BY id");
            while (rsBlocos.next()) {
                String infos = rsBlocos.getString("informacoes");
                blockchain.addBlock(infos);
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

        for (Block bloco : blockchain.getBlockchain()) {
            System.out.println(bloco.imprimirBloco());
        }
    }
}
