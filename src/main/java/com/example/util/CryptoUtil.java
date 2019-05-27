package com.example.util;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

/**
 * @Description: 密码加密解密
 * @Author: xwb007
 * @Date: 2019/5/18 0018 15:30
 * @return CryptoUtil.encode() 加密
 * @return CryptoUtil.decode() 解密
 */

public class CryptoUtil {

    public static Key DEFAULT_KEY = null;
    public static final String DEFAULT_SECRET_KEY = "1qaz2wsx3edc$RFV%TGB^YHN&UJM";
    public static final String DES = "DES";

    static {
        DEFAULT_KEY = obtainKey(DEFAULT_SECRET_KEY);
    }

    /**
     * 获得key
     **/
    public static Key obtainKey(String key) {
        if (key == null) {
            return DEFAULT_KEY;
        }
        KeyGenerator generator = null;
        try {
            generator = KeyGenerator.getInstance(DES);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        generator.init(new SecureRandom(key.getBytes()));
        Key key1 = generator.generateKey();
        generator = null;
        return key1;
    }

    /**
     * 加密<br>
     * String明文输入,String密文输出
     */
    public static String encode(String str) {
        return encode(null, str);
    }

    /**
     * 加密<br>
     * String明文输入,String密文输出
     */
    public static String encode(String key, String str) {
        return Base64.encodeBase64URLSafeString(obtainEncode(key, str.getBytes()));
        // 可以转化为16进制数据
        // return Hex.encodeHexString(obtainEncode(key, str.getBytes()));
    }

    /**
     * 解密<br>
     * 以String密文输入,String明文输出
     */
    public static String decode(String str) {
        return decode(null, str);
    }

    /**
     * 解密<br>
     * 以String密文输入,String明文输出
     */
    public static String decode(String key, String str) {
        return new String(obtainDecode(key, Base64.decodeBase64(str)));
        // 可以转化为16进制的数据
//      try {
//          return new String(obtainDecode(key, Hex.decodeHex(str.toCharArray())));
//      } catch (DecoderException e) {
//          // TODO Auto-generated catch block
//          e.printStackTrace();
//      }
    }

    /**
     * 加密<br>
     * 以byte[]明文输入,byte[]密文输出
     */
    private static byte[] obtainEncode(String key, byte[] str) {
        byte[] byteFina = null;
        Cipher cipher;
        try {
            Key key1 = obtainKey(key);
            cipher = Cipher.getInstance(DES);
            cipher.init(Cipher.ENCRYPT_MODE, key1);
            byteFina = cipher.doFinal(str);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cipher = null;
        }
        return byteFina;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * 解密<br>
     * 以byte[]密文输入,以byte[]明文输出
     */
    private static byte[] obtainDecode(String key, byte[] str) {
        Cipher cipher;
        byte[] byteFina = null;
        try {
            Key key1 = obtainKey(key);
            cipher = Cipher.getInstance(DES);
            cipher.init(Cipher.DECRYPT_MODE, key1);
            byteFina = cipher.doFinal(str);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cipher = null;
        }
        return byteFina;
    }

    /**
     * 测试代码
     */
//    public static void main(String[] args) {
//        String a = "root1122fdsfa3.SS";
//        System.out.println("原密码：" + a);
//
//        String m = encode(a);
//        System.out.println("加密后：" + m);
//
//        String n = decode(m);
//        System.out.println("解密后：" + n);
//    }
}
