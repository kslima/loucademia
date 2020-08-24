package br.com.kslima.loucademia.application.util;

public class StringUtils {

    public static boolean isEmpty(String s) {
        if (s == null) {
            return true;
        }

        return s.trim().length() == 0;
    }

    public static String leftZeroes(int value, int finalSize) {
        return String.format("%0" + finalSize + "d", value);
    }

    public static String doubleToString(String value){
        try {
            return Double.toString(value);
        } catch (){
            throw new ValidatioException("Não é possivel converter para numero");
        }
    }


}
