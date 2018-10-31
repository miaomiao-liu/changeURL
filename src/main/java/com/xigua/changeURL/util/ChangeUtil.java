package com.xigua.changeURL.util;

import com.xigua.changeURL.model.Type;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @Author: miaomiao
 * @Date: 2018/10/30
 * @Description:
 **/
public class ChangeUtil {

    private static char[] CHARS = new char[]{
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };

    public static String[] convert(String longUrl, Type type, int number, String key) {
        if (key == null) {
            //默认key
            key = "fvnaoaehgv";
        }
        if (number > 16){
            number = 16;
        }else if (number < 4){
            number = 4;
        }
        char[] chars;
        switch (type) {
            case CHARS:
                chars = CHARS;
                break;
            case DIGITAL:
                chars = Arrays.copyOfRange(CHARS, 52, 62);
                break;
            case ALPHABET:
                chars = Arrays.copyOfRange(CHARS, 0, 52);
                break;
            case BIGALPHABET:
                chars = Arrays.copyOfRange(CHARS, 26, 52);
                break;
            case SMALLALPHABET:
                chars = Arrays.copyOfRange(CHARS, 0, 26);
                break;
            default:
                chars = CHARS;
        }
        String MD5Result = MD5(longUrl + key);
        String hex = MD5Result;
        String[] shortUrl = new String[4];
        int step = chars.length / number;
        long subHex = chars.length - 1;
        for (int i = 0; i < 4; i++) {
            // 把加密字符按照8位一组16进制
            String sTempSubString = hex.substring(i * 8, i * 8 + 8);
            // 这里需要使用 long 型来转换，因为 Inteter.parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用 long ，则会越界
            long lHexLong = Long.parseLong(sTempSubString, 16);
            StringBuilder outChars = new StringBuilder();
            for (int j = 0; j < number; j++) {
                long index =  subHex & lHexLong;     // 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
                outChars.append(chars[(int) index]);        // 把取得的字符相加
                lHexLong = lHexLong >> step;
            }
            shortUrl[i] = outChars.toString();
        }
        return shortUrl;
    }

    private static String MD5(String originString) {
        if (originString != null) {
            try {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                byte[] results = md5.digest(originString.getBytes());
                StringBuffer resultSb = new StringBuffer();
                for (int i = 0; i < results.length; i++) {
                    resultSb.append(byteToHexString(results[i]));
                }
                return resultSb.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static String byteToHexString(byte b) {
        String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

}
