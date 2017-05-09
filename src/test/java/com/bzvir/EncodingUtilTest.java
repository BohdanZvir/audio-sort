package com.bzvir;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class EncodingUtilTest {
    @Test
    void fixCyrillicEncoding() throws UnsupportedEncodingException {
        String actual = EncodingUtil.fixCyrillicEncoding("Ðóñëàíà");

        assertThat(actual, is("Руслана"));
    }

    @Test
    void isCyrillicChar() {
        boolean isCyrillic = EncodingUtil.isCyrillicChars("Ф");
        assertThat(isCyrillic, is(true));
    }

    @Test
    void isNotCyrillicChar() {
        boolean isCyrillic = EncodingUtil.isCyrillicChars("F");
        assertThat(isCyrillic, is(false));
    }

    @Test
    void isCyrillicChars() {
        boolean isCyrillic = EncodingUtil.isCyrillicChars("Франик");
        assertThat(isCyrillic, is(true));
    }

    @Test
    void isNotCyrillicChars() {
        boolean isCyrillic = EncodingUtil.isCyrillicChars("Fраник");
        assertThat(isCyrillic, is(false));
    }

    @Test
    void isLatinChar() {
        boolean isCyrillic = EncodingUtil.isLatinChars("F");
        assertThat(isCyrillic, is(true));
    }

    @Test
    void isNotLatinChar() {
        boolean isCyrillic = EncodingUtil.isLatinChars("F");
        assertThat(isCyrillic, is(true));
    }

    @Test
    void isLatinChars() {
        boolean isCyrillic = EncodingUtil.isLatinChars("Franko");
        assertThat(isCyrillic, is(true));
    }

    @Test
    void isNotLatinChars() {
        boolean isCyrillic = EncodingUtil.isLatinChars("Fранко");
        assertThat(isCyrillic, is(true));
    }

    @Test
    @Ignore
    void detectEncoding() throws UnsupportedEncodingException {
        String encoding = EncodingUtil.detectEncoding("Ðóñëàíà");
        System.out.println(encoding);
    }
}