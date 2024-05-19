import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Block {
    private int index;
    private long timestamp;
    private String previousHash;
    private String hash;
    private String newOwner;
    private String owner;
    private double latitude;
    private double longitude;

    public Block(int index, String previousHash, String owner, double latitude, double longitude) {
        this.index = index;
        this.timestamp = new Date().getTime();
        this.previousHash = previousHash;
        this.owner = owner;
        this.latitude = latitude;
        this.longitude = longitude;
        this.hash = generateHash();
    }

    public Block(int index, String previousHash, String newOwner) {
        this.index = index;
        this.timestamp = new Date().getTime();
        this.previousHash = previousHash;
        this.newOwner = newOwner;
        this.hash = generateHash();
    }

    public String generateHash() {
        String newOwnerToHash = index + timestamp + previousHash + owner + latitude + longitude;
        MessageDigest digest;
        StringBuilder hexString = new StringBuilder();

        try {
            digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(newOwnerToHash.getBytes());

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

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    } 

    public String getNewOwner() {
        return newOwner;
    }

    public void setNewOwner(String newOwner) {
        this.newOwner = newOwner;
    }

    public String imprimirBloco() {

        if (getPreviousHash() == "0") {
            return "Bloco Gênesis :\nnewOwner e Hora: " + getFormattedTimestamp() + "\nProprietário(a): " + getOwner() + "\nLatitude: " +   getLatitude() + "\nLongitude: " + getLongitude() + "\nHash: " + getHash() + "\n";
        }else {
            return "Bloco " + getIndex() + ":\nnewOwner e Hora: " + getFormattedTimestamp() + "\nResponsável: " + getNewOwner() +"\nHash anterior: "
                + getPreviousHash() + "\nHash: " + getHash() + "\n";
        }
    }
}
