package Clases;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Contraseña_Boreal {

    String pass = "boreal01", pass_codigo = "", contraseña = "", cadenaFecha = "", parte_suma = "";
    Date fecha1 = new Date();
    String[][] codigo = {{"A", "21"}, {"B", "22"}, {"C", "23"}, {"a", "25"}, {"b", "26"}, {"c", "27"}, {"D", "31"}, {"E", "32"}, {"F", "33"}, {"d", "35"}, {"e", "36"}, {"f", "37"}, {"G", "41"}, {"H", "42"}, {"I", "43"}, {"g", "45"}, {"h", "46"}, {"i", "47"}, {"J", "51"}, {"K", "52"}, {"L", "53"}, {"j", "55"}, {"k", "56"}, {"l", "57"}, {"M", "61"}, {"N", "62"}, {"O", "63"}, {"m", "65"}, {"n", "66"}, {"o", "67"}, {"P", "71"}, {"Q", "72"}, {"R", "73"}, {"S", "74"}, {"p", "75"}, {"q", "76"}, {"r", "77"}, {"s", "78"}, {"T", "81"}, {"U", "82"}, {"V", "83"}, {"t", "85"}, {"u", "86"}, {"v", "87"}, {"W", "91"}, {"X", "92"}, {"Y", "93"}, {"Z", "94"}, {"w", "95"}, {"x", "96"}, {"y", "97"}, {"z", "98"}, {"0", "00"}, {"1", "10"}, {"2", "20"}, {"3", "30"}, {"4", "40"}, {"5", "50"}, {"6", "60"}, {"7", "70"}, {"8", "80"}, {"9", "90"}};
    int largo, i = 0, j = 0, largo1, largo2;

    char letra;

    public String Boreal_Contraseña() {
        
        largo = pass.length();

        while (i < largo) {

            letra = pass.charAt(i);

            while (j < 62) {

                if (codigo[j][0].equals(String.valueOf(letra))) {

                    pass_codigo = pass_codigo + codigo[j][1];
                    j = 62;
                }
                j++;
            }
            j = 0;
            i++;
        }
        largo1 = pass_codigo.length();
        largo2 = largo1 - 10;

        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
        cadenaFecha = formato.format(fecha1);

        contraseña = pass_codigo.substring(0, largo2) + String.valueOf(Long.valueOf(pass_codigo.substring(largo2, largo1)) + Integer.valueOf(cadenaFecha));

        System.out.println("Clave:" + contraseña);
        return contraseña;
    }
}
