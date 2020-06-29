package Clases;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class TripleDes {

    /*Encripta usando el Algoritmo Triple DES en Base64*/
    public String EncriptarStr(String pszDatos, String pszKey) {
        byte[] woKey, woDatos, woResultado, woTmp;
        SecretKey woSKey;
        Cipher woCipher;
        BASE64Encoder woBase64;
        String wszStrBase64;
        int wiPaddingWidth, wiIdx = 0;
        try {
            woKey = pszKey.getBytes("UTF8");
            woDatos = pszDatos.getBytes("UTF8");
            woBase64 = new BASE64Encoder();
            System.out.println(pszDatos);
//Padding Zero
            woTmp = new byte[(pszDatos.length() / 8 + 1) * 8];
//Caso: la longitud de la cadena a encriptar no es multiplo de 8.-
            if (pszDatos.length() % 8 != 0) {
                wiPaddingWidth = (pszDatos.length() / 8 + 1) * 8;
                for (wiIdx = 0; wiIdx < wiPaddingWidth; wiIdx++) {
                    if (wiIdx < woDatos.length) {
                        woTmp[wiIdx] = woDatos[wiIdx];
                    } else {
                        woTmp[wiIdx] = 0;
                    }
                }
            } else //Caso: la longitud de la cadena a encriptar en multiplo de 8.-
            {
                woTmp = woDatos;
            }
//Género la clave secreta
            woSKey = new SecretKeySpec(woKey, "DESede");
            woCipher = Cipher.getInstance("DESede/ECB/NoPadding");
            woCipher.init(Cipher.ENCRYPT_MODE, woSKey);
            woResultado = woCipher.doFinal(woTmp);
            wszStrBase64 = woBase64.encodeBuffer(woResultado);
            return wszStrBase64;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public String DesencriptarStr(String pszDatos, String pszKey) {
        byte[] woKey, woDatos, woResultado;
        SecretKey woSKey;
        Cipher woCipher;
        BASE64Decoder woBase64Dec;
        String wszResultado;
        try {
            woBase64Dec = new BASE64Decoder();
            woKey = pszKey.getBytes("UTF8");
            woDatos = woBase64Dec.decodeBuffer(pszDatos);
//Género la clave secreta
            woSKey = new SecretKeySpec(woKey, "DESede");
            woCipher = Cipher.getInstance("DESede/ECB/NoPadding");
            woCipher.init(Cipher.DECRYPT_MODE, woSKey);
            woResultado = woCipher.doFinal(woDatos);
            wszResultado = new String(woResultado);
            return wszResultado;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}