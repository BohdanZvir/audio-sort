package com.bzvir;

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

}