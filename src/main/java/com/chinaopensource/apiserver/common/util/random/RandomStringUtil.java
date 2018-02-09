package com.chinaopensource.apiserver.common.util.random;

import java.util.Random;

/**
 * create by lzl ON 2018/02/09
 */
public class RandomStringUtil {

    //生成nickName的长度
    public static final int NUMBER = 6;

    /**
     * 自定义进制(0,1没有加入,容易与o,l混淆)
     */
    private static final char[] binset = new char[]{'8', 'q', 'w', '2', 'e', 'a', '3', 's', 'd', 'z', '4', 'x', 'c',
            'p', 'i', '6', 'k', 'm', 'j', '7', 'u', 'f', '5', 'r',
            'v', 'y', 'l', 't', 'n', 'b', 'g', 'h'};

    /**
     * (不能与自定义进制有重复)
     */
    private static final char separator = 'o';

    /**
     * 进制长度
     */
    private static final int binLen = binset.length;

    /**
     * 根据ID生成随机码
     *
     * @param id
     * @param length
     * @return
     */
    public static String toSerialCode(long id, int length) {
        char[] buf = new char[32];
        int charPos = 32;

        while ((id / binLen) > 0) {
            int ind = (int) (id % binLen);
            buf[--charPos] = binset[ind];
            id /= binLen;
        }
        buf[--charPos] = binset[(int) (id % binLen)];
        String str = new String(buf, charPos, (32 - charPos));
        // 不够长度的自动随机补全
        if (str.length() < length) {
            StringBuilder sb = new StringBuilder();
            sb.append(separator);
            Random rnd = new Random();
            for (int i = 1; i < length - str.length(); i++) {
                sb.append(binset[rnd.nextInt(binLen)]);
            }
            str += sb.toString();
        }
        return str;
    }

    public static long codeToId(String code) {
        char chs[] = code.toCharArray();
        long result = 0L;
        for (int i = 0; i < chs.length; i++) {
            int ind = 0;
            for (int j = 0; j < binLen; j++) {
                if (chs[i] == binset[j]) {
                    ind = j;
                    break;
                }
            }
            if (chs[i] == separator) {
                break;
            }
            if (i > 0) {
                result = result * binLen + ind;
            } else {
                result = ind;
            }
        }
        return result;
    }
}
