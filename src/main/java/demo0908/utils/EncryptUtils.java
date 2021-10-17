package demo0908.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {
    public static String digest(String message)  {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] targetBytes = md5.digest(message.getBytes());
        char[] characters = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        //4.遍历targetBytes
        StringBuilder builder = new StringBuilder();
        for (byte b : targetBytes) {
            //5.取出b的高四位的值
            //先把高四位通过右移操作拽到低四位
            int high = (b >> 4) & 15;

            //6.取出b的低四位的值
            int low = b & 15;

            //7.以high为下标从characters中取出对应的十六进制字符
            char highChar = characters[high];

            //8.以low为下标从characters中取出对应的十六进制字符
            char lowChar = characters[low];

            builder.append(highChar).append(lowChar);
        }

        return builder.toString();
    }
}