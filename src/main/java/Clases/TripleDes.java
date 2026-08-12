package Clases;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class TripleDes {

    /*Encripta usando el Algoritmo Triple DES en Base64*/
    public String EncriptarStr(String pszDatos, String pszKey) {
        byte[] woKey, woDatos, woResultado, woTmp;
        SecretKey woSKey;
        Cipher woCipher;
        String wszStrBase64;
        int wiPaddingWidth, wiIdx = 0;
        try {
            woKey = pszKey.getBytes("UTF8");
            woDatos = pszDatos.getBytes("UTF8");
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
            wszStrBase64 = Base64.getEncoder().encodeToString(woResultado) + "\n";
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
        String wszResultado;
        try {
            woKey = pszKey.getBytes("UTF8");
            woDatos = Base64.getDecoder().decode(pszDatos.trim());
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
