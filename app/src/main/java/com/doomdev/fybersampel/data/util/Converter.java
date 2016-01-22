package com.doomdev.fybersampel.data.util;

import com.doomdev.fybersampel.data.pojo.request.RequestContent;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;

/**
 * This class contain static methods to convert different inputs
 * Created by and on 13.01.16.
 */
public class Converter {
    private static final String TAG = Converter.class.getSimpleName();
    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    /**
     * This method create signature (SHA1) and concatenate values of giving map as url parameter.
     *
     * @param map    map with parameter to be set in to the url
     * @param apiKey api key to concatenate with the parameters string
     * @return {@link RequestContent} containing signature and valid url
     */
    public static RequestContent createHashKex(Map<String, String> map,String apiKey) {
        if (map == null) {
            return null;
        }
        String hashkey = null;
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        boolean firstRun = true;
        while (it.hasNext()) {
            if (!firstRun) {
                sb.append("&");
            } else {
                firstRun = false;
            }
            Map.Entry<String, String> entry = it.next();
            sb.append(entry.getKey()).append("=").append(entry.getValue());
        }
        String url = sb.toString();
        sb.append("&");
        sb.append(apiKey);

        hashkey = sha1Hash(sb.toString());
        StringBuilder strBuilder = new StringBuilder(Const.FULL_URL);
        strBuilder.append(url).append("&").append("hashkey=").append(hashkey.toLowerCase());
        return new RequestContent(strBuilder.toString(),hashkey);
    }

    /**
     * Encrypt giving String using secure hash algorithm (SHA1)
     *
     * @param toHash string to encrypt with SHA1
     * @return encrpted string with SHA1
     */
    public static String sha1Hash(String toHash) {
        if (toHash == null) {
            return null;
        }
        String hash = null;
        try
        {
            MessageDigest digest = MessageDigest.getInstance( "SHA-1" );
            byte[] bytes = toHash.getBytes("UTF-8");
            digest.update(bytes, 0, bytes.length);
            bytes = digest.digest();

            // This is ~55x faster than looping and String.formating()
            hash = bytesToHex( bytes );
        }
        catch( NoSuchAlgorithmException e )
        {
            e.printStackTrace();
        }
        catch( UnsupportedEncodingException e )
        {
            e.printStackTrace();
        }
        return hash;
    }

    /**
     * Convert giving bytes in to hex string
     *
     * @param bytes bytes to convert in to hex string
     * @return converted bytes as hex string
     */
    private static String bytesToHex(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        char[] hexChars = new char[ bytes.length * 2 ];
        for( int j = 0; j < bytes.length; j++ )
        {
            int v = bytes[ j ] & 0xFF;
            hexChars[ j * 2 ] = hexArray[ v >>> 4 ];
            hexChars[ j * 2 + 1 ] = hexArray[ v & 0x0F ];
        }
        return new String( hexChars );
    }
}
