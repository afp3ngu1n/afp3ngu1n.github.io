import java.util.*;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class crypt {
        
    public static void main(String[] str) throws Exception{
        
        // message to be encrypted, encoded as array of bytes
        String messages = "thisismysecretmessage";
        byte[] message = messages.getBytes();
        
        // set parameters
        //IV 16bytes random string in bytes
        byte ivb[] = new byte[16];
        (new Random()).nextBytes(ivb);
        IvParameterSpec iv = new IvParameterSpec(ivb);
        String ivs = Base64.getEncoder().encodeToString(ivb);   
        //Key 16bytes
        String key = "secretkey16bytes";
        byte[] keyb = key.getBytes();
        SecretKeySpec secretKey = new SecretKeySpec(keyb, "AES");
        
        //Encryption
        //create cipher object
        Cipher encrypter = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        encrypter.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        //encrypt
        byte[] encryptedMsgb = encrypter.doFinal(message);
        //change to readable format: (a) byte to Base64 encode and (b) convert to string
        String encryptedMsg = Base64.getEncoder().encodeToString(encryptedMsgb);
        
        //output
        System.out.println("The encrypted message is: " + encryptedMsg);
        System.out.println("The IV is: " + ivs);
        System.out.println("The key is: " + key);

        //decryption
        
        //create cipher object
        Cipher decrypter = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        decrypter.init(Cipher.DECRYPT_MODE, secretKey, iv);
        //decrypt
        //convert encrypted message to bytes: (a) string to bytes then (b) base64 decode bytes
        byte[] encryptMsgb = Base64.getDecoder().decode(encryptedMsg.getBytes());
        String decryptedMsg = new String(decrypter.doFinal(encryptMsgb));
        
        //output
        System.out.println("The decrypted message is: " + decryptedMsg); 
    }
}
