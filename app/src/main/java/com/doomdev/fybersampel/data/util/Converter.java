package com.doomdev.fybersampel.data.util;

import android.util.Base64;
import android.util.Log;

import com.doomdev.fybersampel.data.net.RestApi;
import com.doomdev.fybersampel.data.pojo.request.RequestContent;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by and on 13.01.16.
 */
public class Converter {
    private static final String TAG = Converter.class.getSimpleName();
    private final static String HEX = "0123456789ABCDEF";

    public static RequestContent createHashKex(Map<String, String> map,String apiKey) {
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
        Log.d(TAG, "sha1: " + hashkey);
        StringBuilder strBuilder = new StringBuilder(RestApi.URL);
        strBuilder.append(url).append("&").append("hashkey=").append(hashkey.toLowerCase());
        return new RequestContent(strBuilder.toString(),hashkey);
    }

    public static String sha1Hash( String toHash )
    {
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

    // http://stackoverflow.com/questions/9655181/convert-from-byte-array-to-hex-string-in-java
    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex( byte[] bytes )
    {
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
