package com.bzvir;

import org.mozilla.universalchardet.UniversalDetector;

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

    static String detectEncoding(String text) throws UnsupportedEncodingException {
        UniversalDetector detector = new UniversalDetector(null);
// (2)
        byte[] bytes = text.getBytes();
        detector.handleData(bytes, 0, bytes.length);
// (3)
        detector.dataEnd();

// (4)
        String encoding = detector.getDetectedCharset();
// (5)
        detector.reset();
        return encoding;
    }
}