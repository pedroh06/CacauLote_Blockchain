package com.example;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Block {
    private int index;
    private long timestamp;
    private String previousHash;
    private String hash;
    private String infos;
    private String owner;
    private String cpf;
    private double latitude;
    private double longitude;
    private String plantacionInfos;

    public Block(int index, String previousHash, String owner, String cpf, double latitude, double longitude, String plantacionInfos) {
        this.index = index;
        this.timestamp = new Date().getTime();
        this.previousHash = previousHash;
        this.owner = owner;
        this.cpf = cpf;
        this.latitude = latitude;
        this.longitude = longitude;
        this.plantacionInfos = plantacionInfos;
        this.hash = generateGenesisHash();
    }

    public Block(int index, String previousHash, String infos) {
        this.index = index;
        this.timestamp = new Date().getTime();
        this.previousHash = previousHash;
        this.infos = infos;
        this.hash = generateHash();
    }

    public String generateGenesisHash() {
        String hash = index + timestamp + previousHash + owner + cpf + latitude + longitude + plantacionInfos;
        MessageDigest digest;
        StringBuilder hexString = new StringBuilder();

        try {
            digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(hash.getBytes());

            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return hexString.toString();
    }

    public String generateHash() {
        String hash = index + timestamp + previousHash + infos;
        MessageDigest digest;
        StringBuilder hexString = new StringBuilder();

        try {
            digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(hash.getBytes());

            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return hexString.toString();
    }

    public String getFormattedTimestamp() {
        Date date = new Date(timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return sdf.format(date);
    }

    public int getIndex() {
        return index;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getHash() {
        return hash;
    }

    public String getOwner() {
        return owner;
    }

    public String getCpf() {
        return cpf;
    }

    public String getPlantacionInfos() {
        return plantacionInfos;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    } 

    public String getinfos() {
        return infos;
    }

    public void setinfos(String infos) {
        this.infos = infos;
    }

    public String imprimirBloco() {

        if (getPreviousHash() == "0") {
            return "Bloco Gênesis :\nData e Hora: " + getFormattedTimestamp() + "\nProprietário(a): " + getOwner() + "\nCPF do(a) proprietário(a): " + getCpf() + "\nLatitude: " +   getLatitude() + "\nLongitude: " + getLongitude() + "\nInformações da plantação: " + getPlantacionInfos() + "\nHash: " + getHash() + "\n";
        }else {
            return "Bloco " + getIndex() + ":\nData e Hora: " + getFormattedTimestamp() + "\nInformações: " + getinfos() +"\nHash anterior: "
                + getPreviousHash() + "\nHash: " + getHash() + "\n";
        }
    }
}