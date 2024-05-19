package com.example;
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
    }
}
