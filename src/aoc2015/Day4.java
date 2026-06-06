package aoc2015;


import common.Day;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author permi
 */
public class Day4 extends Day {

    private static final int LEADING_ZEROS = 5;
    
    public Day4() throws NoSuchAlgorithmException {
        super(FileType.Input);
        
        String prefix = "";
        for(int i = 0; i < LEADING_ZEROS; i++) {
            prefix += '0';
        }

        StringBuilder sb = new StringBuilder();
        MessageDigest md5 = MessageDigest.getInstance("MD5");

        int i = 0;
        do {
            i++;
            
            byte[] hashBytes = md5.digest((input + i).getBytes());
            
            // convert to hex string
            sb.setLength(0);
            for (byte b : hashBytes) {
                // 1 byte = 2 hex digits
                sb.append(String.format("%02x", b));
            }
            
        } while(!sb.substring(0, LEADING_ZEROS).equals(prefix));

        System.out.println("Calculated md5(" + sb + ") for input: " + input + i);
    }

}
