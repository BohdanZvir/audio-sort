package com.bzvir;

import java.io.UnsupportedEncodingException;

public class EncodingUtil {

    static String fixCyrillicEncoding(String text) throws UnsupportedEncodingException {
        if (isLatinChars(text)) {
            return text;
        } else if (isCyrillicChars(text)) {
            return text;
        } else {
            String newStr = new String(text.getBytes(FileFixer.ISO_8859_1), FileFixer.WINDOWS_1251);
            if (isCyrillicChars(newStr)) {
                return newStr;
            }

            throw new IllegalArgumentException("not supported");
        }
    }

    static boolean isCyrillicChars(String text) {
        return Character.UnicodeBlock.of(text.charAt(0)).equals(Character.UnicodeBlock.CYRILLIC);
    }

    static boolean isLatinChars(String text) {
        return Character.UnicodeBlock.of(text.charAt(0)).equals(Character.UnicodeBlock.BASIC_LATIN);
    }

}