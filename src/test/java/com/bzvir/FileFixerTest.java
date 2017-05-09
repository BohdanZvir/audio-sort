package com.bzvir;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit test for simple App.
 */
@RunWith(PowerMockRunner.class)
//@PrepareForTest(FileFixer.class)
public class FileFixerTest {

    private String testFile = "test_data/1Руслана - Світанок1.mp3";

    @Test
    public void renameFileByArtistDashSongNameDotMp3() throws InvalidDataException, IOException, UnsupportedTagException {
        String fileName = FileFixer.fixFileName(testFile);
        assertThat(fileName, is("Руслана - Світанок.mp3"));
    }

    @Test
    public void fixCyrillicEncoding() throws UnsupportedEncodingException {
//        PowerMockito.spy(FileFixer.class);git s
//        PowerMockito.doReturn()
        String win1251 = "Ðóñëàíà";
        String utf8 = "Руслана";

        String actual = FileFixer.fixCyrillicEncoding(win1251);

        assertThat(actual, is(utf8));
    }
}
