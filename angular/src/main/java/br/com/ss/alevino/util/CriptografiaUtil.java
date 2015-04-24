package br.com.ss.alevino.util;

import java.security.MessageDigest;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

@SuppressWarnings("restriction")
public class CriptografiaUtil {
	
	/** Cifra com 32 Caracteres. */
	public static final String MD5 = "MD5";
	
	/** Cifra com 64 Caracteres. Default. */
	public static final String SHA_256 = "SHA-256";
	

    public static String criptografar( final String txt) {
    	return criptografar(txt, SHA_256);
    }

    public static String criptografar( final String txt, String algoritmo ) {
    	String cript = null;
        try {
            MessageDigest md = MessageDigest.getInstance( algoritmo );
            md.update( txt.getBytes() );
            cript = HexBin.encode( md.digest() );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return cript;
    }

    public static void main( String[] args ) {
    	
    	String txt = "admin";
		String md5 = criptografar(txt, MD5),
    			sha = criptografar(txt );
    	
        System.out.println( md5 );
        System.out.println( sha );
    }
    
}
